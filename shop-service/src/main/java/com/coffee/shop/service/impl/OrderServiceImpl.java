package com.coffee.shop.service.impl;

import com.coffee.common.exception.BadRequestException;
import com.coffee.common.exception.NotFoundException;
import com.coffee.common.utils.ErrorMessages;
import com.coffee.common.utils.JwtUtils;
import com.coffee.common.utils.UserInfo;
import com.coffee.shop.entity.Order;
import com.coffee.shop.entity.OrderDetail;
import com.coffee.shop.entity.Queue;
import com.coffee.shop.entity.ShopProduct;
import com.coffee.shop.mapper.OrderDetailMapper;
import com.coffee.shop.model.CustomerInfo;
import com.coffee.shop.model.OrderDetailRequest;
import com.coffee.shop.model.OrderDetailResponse;
import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;
import com.coffee.shop.repository.OrderDetailRepository;
import com.coffee.shop.repository.OrderRepository;
import com.coffee.shop.repository.QueueRepository;
import com.coffee.shop.repository.ShopProductRepository;
import com.coffee.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final QueueRepository queueRepository;
    private final ShopProductRepository shopProductRepository;
    private final OrderDetailMapper orderDetailMapper;

    private static final int TIME_PER_ORDER = 5; // time in minutes for each order


    @Override
    @Transactional
    public OrderResponse createOrder(Long shopId , OrderRequest orderRequest) {
        Queue selectedQueue = findQueueForNewOrder(shopId);

        var customer = JwtUtils.getUserInfo();
        var order = validateAndSaveOrder(customer, shopId, orderRequest, selectedQueue);

        return convertToOrderResponse(order, customer);

        //  Send Notification
        // When an Order is saved to the database, a Kafka Connect source listens for this update.
        // Upon detecting an updated Order, the Kafka Connect source sends a message to a Kafka topic.
        // The Notification Service is a consumer of this Kafka topic.
        // When it receives a message, it sends an appropriate notification to the customer.
    }

    @Override
    public List<Order> getOrdersByShopId(Long shopId) {
        return orderRepository.findByShopId(shopId);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ErrorMessages.RESOURCE_NOT_FOUND));

        var response = OrderResponse.builder().build();
        response.setOrderId(order.getId());
        response.setCustomer(CustomerInfo.builder().customerId(order.getCustomerId()).build());
        response.setShopId(order.getShopId());
        response.setQueueId(order.getQueueId());
        response.setTotalPrice(order.getTotalPrice());
        response.setPositionInQueue(order.getPositionInQueue());
        response.setExpectedWaitTime(order.getExpectedWaitTime());
        response.setOrderStatus(order.getOrderStatus());

        // Convert OrderDetails to OrderDetailResponse
        List<OrderDetailResponse> detailResponses = new ArrayList<>();
        for (OrderDetail detail : order.getOrderDetails()) {
            OrderDetailResponse detailResponse = OrderDetailResponse.builder()
                    .shopProductId(detail.getShopProductId())
                    .quantity(detail.getQuantity())
                    .build();
            detailResponses.add(detailResponse);
        }

        response.setDetails(detailResponses);

        return response;
    }

    private Order validateAndSaveOrder(UserInfo customer, Long shopId, OrderRequest orderRequest, Queue selectedQueue){
        // Check is each product is available and save order
        var orderDetailsRequest = orderRequest.getOrderDetails();

        List<Long> shopProductIds = orderDetailsRequest.stream()
                .map(OrderDetailRequest::getShopProductId)
                .collect(Collectors.toList());

        List<ShopProduct> shopProducts = shopProductRepository.findByIdIn(shopProductIds);

        Map<Long, ShopProduct> shopProductMap = shopProducts.stream()
                .collect(Collectors.toMap(ShopProduct::getId, Function.identity()));

        var orderDetails = new ArrayList<OrderDetail>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order newOrder = new Order();
        for (OrderDetailRequest detail : orderDetailsRequest) {
            if (detail.getQuantity() < 1) {
                throw new BadRequestException(ErrorMessages.PRODUCT_QUANTITY_REQUEST_NOT_VALID);
            }
            ShopProduct shopProduct = shopProductMap.get(detail.getShopProductId());

            if (shopProduct == null) {
                throw new BadRequestException(ErrorMessages.PRODUCT_NOT_FOUND);
            }

            if (shopProduct.getQuantityRemaining() == null || shopProduct.getQuantityRemaining() < detail.getQuantity()) {
                throw new BadRequestException(ErrorMessages.PRODUCT_OUT_OF_STOCK);
            }

            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setShopProductId(detail.getShopProductId());
            newOrderDetail.setQuantity(detail.getQuantity());
            newOrderDetail.setOrder(newOrder);
            orderDetails.add(newOrderDetail);

            totalPrice = totalPrice.add(shopProduct.getPrice().multiply(new BigDecimal(detail.getQuantity())));
        }

        newOrder.setCustomerId(customer.getUserId());
        newOrder.setShopId(shopId);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setOrderDetails(orderDetails);
        newOrder.setOrderStatus("In Queue");
        newOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newOrder.setPositionInQueue(selectedQueue.getCurrentQuantityOrders());
        newOrder.setExpectedWaitTime(TIME_PER_ORDER * newOrder.getPositionInQueue());
        newOrder.setQueueId(selectedQueue.getId());

        return orderRepository.save(newOrder);
    }

    private OrderResponse convertToOrderResponse(Order order, UserInfo userInfo) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .positionInQueue(order.getPositionInQueue())
                .expectedWaitTime(order.getExpectedWaitTime())
                .orderStatus(order.getOrderStatus())
                .customer(new CustomerInfo(userInfo.getUserId(), userInfo.getUsername()))
                .details(orderDetailMapper.toDTOs(order.getOrderDetails()))
                .queueId(order.getQueueId())
                .shopId(order.getShopId())
                .build();
    }

    private Queue findQueueForNewOrder(Long shopId){
        // Find the queue that has the fewest orders and has not exceeded the max size
        List<Queue> queues = queueRepository.findByShopIdOrderByCurrentQuantityOrdersAsc(shopId);
        Queue selectedQueue = null;
        for (Queue queue : queues) {
            if (queue.getCurrentQuantityOrders() < queue.getQueueSize()) {
                selectedQueue = queue;
                break;
            }
        }

        if (selectedQueue == null) {
            throw new BadRequestException(ErrorMessages.ALL_QUEUE_ARE_FULL);
        }
        selectedQueue.setCurrentQuantityOrders(selectedQueue.getCurrentQuantityOrders() + 1);
        return queueRepository.save(selectedQueue);
    }
}

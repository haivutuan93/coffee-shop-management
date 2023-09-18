package com.coffee.authorization.service.impl;

import com.coffee.authorization.entity.RolePermissionRestrict;
import com.coffee.authorization.model.RoleAndPermissionCombine;
import com.coffee.authorization.repository.PermissionRepository;
import com.coffee.authorization.repository.RolePermissionRepository;
import com.coffee.authorization.service.RolePermissionDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolePermissionDataServiceImpl implements RolePermissionDataService {
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;

    @Cacheable(cacheNames = "cachePermissionDatabase")
    public RoleAndPermissionCombine loadPermissionsFromDatabase() {
        log.info("Load permissions from Database");

        var roleAndPermissionCombine = new RoleAndPermissionCombine();
        roleAndPermissionCombine.setPermissions(permissionRepository.findAll());

        List<RolePermissionRestrict> rolePermissionsFromFileRestrict = rolePermissionRepository.findAll();
        roleAndPermissionCombine.setRolePermissionRestricts(rolePermissionsFromFileRestrict.stream().map(
                rolePermissionRestrictFromFile -> new RolePermissionRestrict("ROLE_" + rolePermissionRestrictFromFile.getRole().toUpperCase(), rolePermissionRestrictFromFile.getPermissionsRestrict())
        ).collect(Collectors.toList()));

        return roleAndPermissionCombine;
    }

    @CacheEvict(cacheNames = "cachePermissionDatabase", allEntries = true)
    @Scheduled(fixedRateString = "${cache.cache-permission-database}")
    public void clearCache() {
        // This method will clear the "permissionCache" cache every 30s
    }

}

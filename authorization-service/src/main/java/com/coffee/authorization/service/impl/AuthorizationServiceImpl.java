package com.coffee.authorization.service.impl;

import com.coffee.authorization.entity.Permission;
import com.coffee.authorization.entity.RolePermissionRestrict;
import com.coffee.authorization.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final RolePermissionDataServiceImpl rolePermissionDataService;

    @Cacheable(cacheNames = "cacheCheckUserPermission", key = "#authentication.getName() + '_' + #apiUrl + #apiMethod")
    public boolean hasPermission(Authentication authentication, String apiUrl, String apiMethod){
        // if User has Role => need to check, if not don't need to check
        List userRoles = authentication.getAuthorities().stream().collect(Collectors.toList());
        if(userRoles.isEmpty()) {
            log.info("Check permission, user: {}, url: {}, method: {}, User doesn't have Role - return 403", authentication.getName(), apiUrl, apiMethod);
            return false;
        }

        var roleAndPermissionCombine = rolePermissionDataService.loadPermissionsFromDatabase();

        List<Permission> permissionsDefined = roleAndPermissionCombine.getPermissions();

        String path;
        try {
            path = new java.net.URI(apiUrl).getPath().replaceAll("/+$", "");
        } catch (Exception e){
            log.error("API URL have some errors");
            e.printStackTrace();

            return false;
        }

        //find list permissions in DB match with path request
        var permissionDefinedMatchList = permissionsDefined.stream().filter(
                permissionDefined -> Pattern.compile(permissionDefined.getApiUrl()).matcher(path).matches()
                        && ("ANY".equalsIgnoreCase(permissionDefined.getApiMethod()) || permissionDefined.getApiMethod().equals(apiMethod))
        ).collect(Collectors.toList());

        // if permissions in DB matched with path request => need to check, if not don't need to check
        if(!permissionDefinedMatchList.isEmpty()) {
            //check each permission, if userRoles restrict one permission, return false
            for(Permission permission : permissionDefinedMatchList){
                List<RolePermissionRestrict> rolePermissionsRestrictDefined = roleAndPermissionCombine.getRolePermissionRestricts();
                String permissionKeyMatched = permission.getApiPermission();
                log.info("permissionKeyMatched: {}", permissionKeyMatched);

                // check all userRoles if 1 userRoles restrict permissions, return true
                for (Object userRole : userRoles){
                    if(rolePermissionsRestrictDefined.stream().anyMatch(
                            rolePermissionRestrictDefined -> rolePermissionRestrictDefined.getRole().equals(userRole.toString()) && rolePermissionRestrictDefined.getPermissionsRestrict().contains(permissionKeyMatched)
                    )){
                        log.info("Check permission, user: {}, url: {}, method: {}, permissionKeyMatched: {}, User's role restrict permission to access - return 403", authentication.getName(), path, apiMethod, permissionKeyMatched);
                        return false;
                    }
                }
                log.info("Check permission, user: {}, url: {}, method: {}, permissionKeyMatched: {}, User's roles doesn't restrict permission to access", authentication.getName(), path, apiMethod, permissionKeyMatched);
            }

            log.info("Check permission, user: {}, url: {}, method: {}, User's roles doesn't restrict permission to access - return 200", authentication.getName(), path, apiMethod);
            return true;
        }

        log.info("Check permission, user: {}, url: {}, method: {}, Permission not defined - return 200", authentication.getName(), path, apiMethod);
        return true;
    }

    @CacheEvict(cacheNames = "cacheCheckUserPermission", allEntries = true)
    @Scheduled(fixedRateString = "${cache.cache-check-user-permission}")
    public void clearCache() {
        // This method will clear the "userPermissionCache" cache every 10s
    }
}

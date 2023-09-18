package com.coffee.authorization.service;

import com.coffee.authorization.model.RoleAndPermissionCombine;

public interface RolePermissionDataService {
    RoleAndPermissionCombine loadPermissionsFromDatabase();

    void clearCache();
}

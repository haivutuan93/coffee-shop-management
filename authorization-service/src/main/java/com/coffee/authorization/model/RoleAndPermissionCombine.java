package com.coffee.authorization.model;

import com.coffee.authorization.entity.Permission;
import com.coffee.authorization.entity.RolePermissionRestrict;
import lombok.Data;

import java.util.List;

@Data
public class RoleAndPermissionCombine {
    List<Permission> permissions;
    List<RolePermissionRestrict> rolePermissionRestricts;
}

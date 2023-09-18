package com.coffee.authorization.repository;

import com.coffee.authorization.entity.Permission;
import com.coffee.authorization.entity.RolePermissionRestrict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermissionRestrict, Long> {
}
package com.coffee.authorization.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role_permission_restrict")
public class RolePermissionRestrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    String role;

    @Column(name = "permissions_restrict")
    @ElementCollection
    List<String> permissionsRestrict;

    public RolePermissionRestrict(String role, List<String> permissionsRestrict){
        this.role = role;
        this.permissionsRestrict = permissionsRestrict;
    }
}

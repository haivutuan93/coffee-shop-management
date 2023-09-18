package com.coffee.authorization.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_permission")
    String apiPermission;

    @Column(name = "api_url")
    String apiUrl;

    @Column(name = "api_method")
    String apiMethod;

    @Column(name = "description")
    String description;

}

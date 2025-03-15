package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    boolean existsByName(String name);

}

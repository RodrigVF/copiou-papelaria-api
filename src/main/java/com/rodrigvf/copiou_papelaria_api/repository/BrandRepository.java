package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}

package com.factory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
package com.factory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
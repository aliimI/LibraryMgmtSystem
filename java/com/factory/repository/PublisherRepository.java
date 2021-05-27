package com.factory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
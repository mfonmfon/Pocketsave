package com.pocketsave.africa.nija.pocketsave.data.repository;

import com.pocketsave.africa.nija.pocketsave.data.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
}

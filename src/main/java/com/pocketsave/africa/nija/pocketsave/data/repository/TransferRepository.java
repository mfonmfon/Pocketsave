package com.pocketsave.africa.nija.pocketsave.data.repository;

import com.pocketsave.africa.nija.pocketsave.data.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

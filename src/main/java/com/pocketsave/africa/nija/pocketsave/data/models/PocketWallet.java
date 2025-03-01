package com.pocketsave.africa.nija.pocketsave.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
public class PocketWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customers customer;

    @Version
    private Long version = 0L;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = 0L;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

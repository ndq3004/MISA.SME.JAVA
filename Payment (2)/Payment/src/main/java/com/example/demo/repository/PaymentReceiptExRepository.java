package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PaymentReceipt;

public interface PaymentReceiptExRepository extends JpaRepository<PaymentReceipt, String>{
	public PaymentReceipt findByRefID();
}

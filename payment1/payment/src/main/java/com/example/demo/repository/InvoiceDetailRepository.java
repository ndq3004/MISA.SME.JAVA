package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice_Detail;
import com.example.demo.model.PaymentReceipt;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<Invoice_Detail, String> {
//	public Invoice_Detail findByrefID(PaymentReceipt refID);
}

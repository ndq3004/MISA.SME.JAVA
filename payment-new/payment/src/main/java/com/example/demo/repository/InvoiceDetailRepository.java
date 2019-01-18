package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice_Detail;
import com.example.demo.model.PaymentReceipt;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<Invoice_Detail, String> {
//	public Invoice_Detail findByrefID(PaymentReceipt refID);
	@Query(value="SELECT * FROM `invoice_detail` WHERE ref_id=:id",
            nativeQuery=true)
	public List<Invoice_Detail> findByPaymentId(@Param("id") String id);
}

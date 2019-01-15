package com.example.demo.repository;

import com.example.demo.model.PaymentReceipt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, String> {
//	public PaymentReceipt findByRefID(String RefID);
//
//	public PaymentReceipt findByRefTypeID(String RefTypeID);
//
//	public PaymentReceipt findByReasonTypeID(String reasonTypeID);
//	@Query(value="SELECT * FROM payment_receipt  ORDER BY created_date DESC LIMIT :size OFFSET :index",
//            nativeQuery=true)
//    public List<PaymentReceipt> getpage(@Param("index")long index ,@Param("size") int size );
}

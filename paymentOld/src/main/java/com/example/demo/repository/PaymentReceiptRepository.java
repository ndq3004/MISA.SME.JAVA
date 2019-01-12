package com.example.demo.repository;
import com.example.demo.model.PaymentReceipt;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, String>{

	@Query("SELECT created_date from payment_receipt order by created_date desc offset 0 rows fetch next 10 rows")
	public List<PaymentReceipt> getpage(@Param("index")int index ,@Param("size") int size );
	
	
//	@Query("SELECT count( from payment_receipt  ")
//	public int count()
	
}

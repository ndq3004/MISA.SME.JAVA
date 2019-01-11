package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.PaymentReceiptRepository;

public interface PaymentReceiptService  {

	public List<PaymentReceipt> getAllList();
	public List<PaymentReceipt> getPage(int index,int size);
	public long countTotalRecord();

}

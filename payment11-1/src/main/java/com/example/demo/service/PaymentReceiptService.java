package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.PaymentReceiptRepository;


public interface PaymentReceiptService  {
	public void savePayment();
	public List<PaymentReceipt> getAll();
	public List<PaymentReceipt> getAllList();
	public List<PaymentReceipt> getPage(long index,int size);
	public long countTotalRecord();

}

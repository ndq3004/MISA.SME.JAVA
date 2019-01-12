package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.PaymentReceiptRepository;
@Service
public class PaymentReceiptSeviceIpm implements PaymentReceiptService {

	@Autowired
	private PaymentReceiptRepository paymentRepository;
	
	
	@Override
	public List<PaymentReceipt> getAllList() {
		return paymentRepository.findAll();
	}

	@Override
	public List<PaymentReceipt> getPage(int index, int size) {
		return paymentRepository.getpage(index, size);
	}

	@Override
	public long countTotalRecord() {
		// TODO Auto-generated method stub
		return paymentRepository.count();
	}

//	@Override
//	public long countTotalRecord() {
//		
//		return paymentRepository.count();
//	}

	
	
}

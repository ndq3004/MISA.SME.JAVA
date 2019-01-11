package com.example.demo.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.Reason;
import com.example.demo.repository.PaymentReceiptRepository;

@Service
public class PaymentReceiptSeviceImp implements PaymentReceiptService {
	
	@Autowired
	PaymentReceiptRepository paymentReceiptRepository;
	@Override
	public void savePayment() {
//		List<String> companyName= Arrays.asList("C");
		Random rand = new Random();
		for (int i=1; i<1000; i++) {		
			PaymentReceipt pay=new PaymentReceipt();
			pay.setRefID(UUID.randomUUID().toString());
			pay.setCreatedDate(LocalDate.now());
			pay.setPostedDate(LocalDate.now());
			pay.setAccountObject("MISA");
			pay.setReason(new Reason("reason1", "reason1", null));
			//pay.setInvoices("avbc");
			pay.setRefNoFinance(10000);
			pay.setTotalAmount(2000);
			pay.setAccountObjectID(rand.nextInt(10000));
			System.out.print(pay.getAccountObjectID());
			paymentReceiptRepository.save(pay);
			//System.out.println();
	}
	}
	@Override
	public List<PaymentReceipt> getAll() {
		return paymentReceiptRepository.findAll();
	}
	
	
	@Override
	public List<PaymentReceipt> getAllList() {
		return paymentReceiptRepository.findAll();
	}

	@Override
	public List<PaymentReceipt> getPage(long index, int size) {
		
		return paymentReceiptRepository.getpage(index, size);
	}

	@Override
	public long countTotalRecord() {
		// TODO Auto-generated method stub
		return paymentReceiptRepository.count();
	}
}

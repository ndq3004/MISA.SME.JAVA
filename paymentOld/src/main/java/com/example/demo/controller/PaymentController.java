package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.service.PaymentReceiptService;

@RestController
public class PaymentController {
	
	
	@Autowired
	PaymentReceiptService paymentService;
	@RequestMapping("/getAll")
	public long getAll(){
		
		
		long x=(long)paymentService.countTotalRecord();
		return x;
	}

}

package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Invoice_Detail;
import com.example.demo.repository.InvoiceDetailRepository;

@Service
public class InvoiceDetailServiceImp implements InvoiceDetailService {

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	
	@Override
	public void saveInvoiceDetail() {
		List<String> desc= Arrays.asList("Mua thận","Mua PC","Mua smartphone","Mua nhà","Thuê văn phòng","Thuê xe","Thanh toán hóa đơn","Thanh toán hợp đồng");
		Random rand = new Random();
		double min = 0;
		double max = 100;
		Invoice_Detail inv_detail = new Invoice_Detail();
		for (int i = 1; i < 50; i++) {
			inv_detail.setRefDetailID(UUID.randomUUID().toString());
			inv_detail.setAmountOC(min+(rand.nextDouble()*(max-min)));
			inv_detail.setAmount(inv_detail.getAmountOC()*23000);
			//inv_detail.setDescriptionInvoice(rand.nextInt(desc.size()));
		}
	}

	@Override
	public List<Invoice_Detail> findInvoiceDetailByID() {
		return invoiceDetailRepository.findAll();
	}
}

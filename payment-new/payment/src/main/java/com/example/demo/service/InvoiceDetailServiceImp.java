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
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Service;

import com.example.demo.model.Invoice_Detail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.InvoiceDetailRepository;
import com.example.demo.repository.PaymentReceiptRepository;

@Service
public class InvoiceDetailServiceImp implements InvoiceDetailService {

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	PaymentReceiptRepository paymentRepository;
	@Override
	public void saveInvoiceDetail() {
		List<String> desc= Arrays.asList("Mua thận","Mua PC","Mua smartphone","Mua nhà","Thuê văn phòng","Thuê xe","Thanh toán hóa đơn","Thanh toán hợp đồng");
		Random rand = new Random();
		double min = 0;
		double max = 100;
		for (int i = 1; i < 50; i++) {
			Invoice_Detail inv_detail = new Invoice_Detail();
			PaymentReceipt payment = new PaymentReceipt();
			inv_detail.setRefDetailID(UUID.randomUUID().toString());
			inv_detail.setAmountOC(min+(rand.nextDouble()*(max-min)));
			inv_detail.setAmount(inv_detail.getAmountOC()*23000);	
			//payment.setRefID(rand.nextInt());
			//payment.setRefID(rand);
			List< PaymentReceipt> lst=paymentRepository.findAll();
			int size = lst.size();
			int key = rand.nextInt(size-1);
			PaymentReceipt pay=lst.get(key);
			
			inv_detail.setPayment(pay);
			invoiceDetailRepository.save(inv_detail);
			//inv_detail.setDescriptionInvoice(rand.nextInt(desc.size()));
		}
	}

//	@Override
//	public Invoice_Detail findByRefID(String refID) {
//		return invoiceDetailRepository.findById(refID).get();
//	}
}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.service.InvoiceDetailService;
import com.example.demo.service.PaymentReceiptService;
import com.example.demo.service.RefService;

@RestController
public class controller {

	@Autowired
	PaymentReceiptService payservice;
	@Autowired
	RefService refservice;
	@Autowired
	InvoiceDetailService invoiceservice;

	@RequestMapping(value = "/saveData", method = RequestMethod.GET)
	public void save() {
		refservice.saveRef();
		payservice.savePayment();
		invoiceservice.saveInvoiceDetail();
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<PaymentReceipt> getAll() {
		List<PaymentReceipt> list = payservice.getAll();
		return list;
	}

	@RequestMapping("/getAllPage_Size:{size}")
	public Map<String, Object> getAllPage(@PathVariable("size")int size) {
		System.out.println("size:"+size);
		long totalPage = 0;
		Map<String, Object> map = new HashMap<>();
		long x =  payservice.countTotalRecord();
		if (x == 0) {
			map.put("totalPage", totalPage);
			map.put("data", null);
			return map;}
		else if (x % size == 0) {
				totalPage = x / size;}
			else {
				totalPage = x / size + 1;}
		List<PaymentReceipt> lst = payservice.getPage(0, size);
		map.put("totalPage", totalPage);
		map.put("totalRecord", x);
		map.put("data", lst);
		return map;
	}

	@RequestMapping("/getPage/page:{page}_size:{size}")
	public List<PaymentReceipt> get(@PathVariable("page") int page,@PathVariable("size") int size) {
		
		System.out.println(page);
		System.out.println(size);
		long countRecord =  payservice.countTotalRecord();
		long index=page*size;
		List<PaymentReceipt> lst=payservice.getPage(index, size);
		
		return lst;
		
	}
}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.Authenticate;
import com.example.demo.model.Invoice_Detail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.Reason;
import com.example.demo.service.InvoiceDetailService;
import com.example.demo.service.PaymentReceiptService;
import com.example.demo.service.RefService;

import ch.qos.logback.core.status.Status;

@RestController
public class controller {

	@Autowired
	PaymentReceiptService payservice;
	@Autowired
	RefService refservice;
	@Autowired
	InvoiceDetailService invoiceservice;
//	@Autowired
//	InvoiceDetailService invoiceservice;

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
	public Map<String, Object> getAllPage(@PathVariable("size")int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		//Authenticate.Auth(httpServletRequest, httpServletResponse); 
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
	//them chung tu
	@PostMapping("/addRef")
	public Map<String, Object> addRef(@RequestBody PaymentReceipt payment){
		Map<String, Object> map=new HashMap<String, Object>();
		if(payment.getReason().getJournalMemo()==null) {
			
		}
//		Reason reason=re
		Boolean status=payservice.add(payment);
		if(status) map.put("message", "success");
		else map.put("message", "fail");
		return map;
	}
	
	//them hoa don chi tiet
	@PostMapping("/addInvoice:{idPayment}")
	public Map<String, Object> addInvoice(@RequestBody Invoice_Detail invoice,@PathVariable String idPayment){
		Map<String, Object> map=new HashMap<String, Object>();
		
		if(invoice.getDescriptionInvoice()==null) {
		 map.put("message", "Description not empty!");
		return map;
		}
		if(invoice.getAccountObjectID()<=0) {
			 map.put("message", "fail!");
				return map;
		}
		if(invoice.getAmount()<=0) {
			 map.put("message", "fail!");
				return map;
		}
		PaymentReceipt paymentReceipt=payservice.getPaymentById(idPayment);
		Double total=paymentReceipt.getTotalAmount()+invoice.getAmount();
		paymentReceipt.setTotalAmount(total);
		invoice.setPayment(paymentReceipt);
		payservice.update(paymentReceipt);
		Boolean status=invoiceservice.add(invoice);
		if(status) map.put("message", "success!");
		else map.put("message", "fail!");
		return map;
	}
	
	//sua 
	@PostMapping("/updateRef")
	public Map<String, Object> updateRef(@RequestBody PaymentReceipt payment){
		Map<String, Object> map=new HashMap<String, Object>();
		if(payment.getReason()==null) {
			map.put("message", "fail!");
			return map;
		}

		if(payment.getRef()==null) {
			map.put("message", "fail!");
			return map;
		}

		Boolean status=payservice.update(payment);
		if(status) map.put("message", "success!");
		else map.put("message", "fail!");
		return map;
	}
	//sua 
	@PostMapping("/updateInvoice:{idPayment}")
	public Map<String, Object> updateInvoice(@RequestBody Invoice_Detail invoice, @PathVariable String idPayment){
		Map<String, Object> map=new HashMap<String, Object>();
		PaymentReceipt paymentReceipt=payservice.getPaymentById(idPayment);
		Double total=paymentReceipt.getTotalAmount()+invoice.getAmount();
		paymentReceipt.setTotalAmount(total);
		invoice.setPayment(paymentReceipt);
		payservice.update(paymentReceipt);
		Boolean status=invoiceservice.update(invoice);
		if(status) map.put("message", "success!");
		else map.put("error", "fail!");
		return map;
	}
	//xoa
	@PostMapping("/deleteRef")
	public Map<String, Object> deleteRef(@RequestBody PaymentReceipt pay){
			Map<String, Object> map=new HashMap<String, Object>();
		
		Boolean status=payservice.delete(pay);
		if(status) map.put("message", "success!");
		else map.put("error", "fail!");
		return map;
	}
	//xoa 
	@PostMapping("/deleteInvoice:{idPayment}")
	public Map<String, Object> deleteInvoice(@RequestBody Invoice_Detail invoice,@PathVariable String idPayment){
		Map<String, Object> map=new HashMap<String, Object>();
		PaymentReceipt paymentReceipt=payservice.getPaymentById(idPayment);
		
		Double total=paymentReceipt.getTotalAmount()-invoice.getAmount();
		paymentReceipt.setTotalAmount(total);
		payservice.update(paymentReceipt);	
		Boolean status=invoiceservice.remove(invoice);
		if(status) map.put("message", "success!");
		else map.put("error", "fail!");
		return map;
	}
	
	
	@RequestMapping("/getInvoiceDetail:{idRef}")
	public List<Invoice_Detail> getInvoiceDetail(@PathVariable String idRef){
		return invoiceservice.getInvoice_Details(idRef);
	}
}

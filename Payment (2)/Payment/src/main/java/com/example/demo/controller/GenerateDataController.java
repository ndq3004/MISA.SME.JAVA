package com.example.demo.controller;



import java.sql.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;

@RestController
public class GenerateDataController {

	
	@Autowired
	RefTypeService refService;
	@Autowired
	PaymentReceiptService payService;
	@Autowired
	InvoiceDetailService invoiceService;
	@RequestMapping("/ref")
	public String ref() {
		RefType ref=new RefType(1,"thu");
		RefType ref2=new RefType(2,"chi");
		refService.Save(ref);
		refService.Save(ref2);
		return "ref";
	}

	
	@RequestMapping("/payment")
	public String paymnet() {
		List<RefType> ref=refService.findAll();
		String[] keyCompany= {"company1"};
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"hoang","ha","nam","Hieu","Hung","Ngoc"};	
		System.out.println(accountObjectAddresss[rand.nextInt(5)]);
		java.util.Date date=new java.util.Date();
				for(int i=1;i<100000;i++) {
				PaymentReceipt payment=new PaymentReceipt();
				payment.setAccountObjectAddress(accountObjectAddresss[rand.nextInt(5)]);
				payment.setAccountObjectContactName(accountObjectContactNames[rand.nextInt(5)]);
				payment.setAccountObjectID("accountObjectID"+i%10);
				payment.setAccountObjectName(accountObjectNames[rand.nextInt(5)]);
				payment.setCreatedBy(createdBys[rand.nextInt(5)]);
				payment.setCreatedDate(new Date(date.getTime()));
				payment.setDocumentInclude("documentInclude"+i%100+".doc");
				payment.setEditVersion(new Date(date.getTime()));
				payment.setJournalMemo("Trả lương nhân viên tháng "+(i%12+1));
				payment.setKeyCompany(keyCompany[0]);
				payment.setModifiedBy(createdBys[rand.nextInt(5)]);
				payment.setModifiedDate(new Date(date.getTime()));
				payment.setPostedDate(new Date(date.getTime()));
				payment.setRefDate(new Date(date.getTime()));
				payment.setRefNoFinance("CT"+i);
				payment.setRefOrdef(i);
				payment.setTotalAmount(Double.valueOf(0.0));
				payment.setTotalAmountOC(Double.valueOf(0.0));
				payment.setRef(ref.get(i%2));
				payService.save(payment);
				}
		return "payment";
	}
	
	
	@RequestMapping("/invoice")
	public String invoice() {
		
		String[] keyCompany= {"company1","company2","company3","company4","company5"};
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"hoang","ha","nam","Hieu","Hung","Ngoc"};	
		List<PaymentReceipt> payments=payService.getPaymentReceiptOfCompany("company1");
		if(payments==null) {
			return "fails";
		}
		for(int i=0;i<1000;i++) {
			InvoiceDetail invoice=new InvoiceDetail();
			invoice.setAccountObjectID("accountObjectID"+i%10);
			invoice.setAmount(Double.valueOf(1000+i/100));
			invoice.setAmountOC(Double.valueOf(1000+i/1000));
			invoice.setDiscription("Tra luong nhan vien thang"+(i%12+1));
			invoice.setPayment(payments.get(rand.nextInt(payments.size())));
			invoice.setSortOrder(i);
		invoiceService.save(invoice);
		}
		return "invoice";
	}
}

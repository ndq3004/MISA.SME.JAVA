package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Invoice_Detail;

public interface InvoiceDetailService {
	public void saveInvoiceDetail();
	public List<Invoice_Detail> findInvoiceDetailByID(); 
	
}

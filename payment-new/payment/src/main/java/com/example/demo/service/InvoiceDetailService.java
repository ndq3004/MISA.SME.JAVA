package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Invoice_Detail;

public interface InvoiceDetailService {
	public void saveInvoiceDetail();
	//public Invoice_Detail findByRefID(String refID); 
	public boolean add(Invoice_Detail invoice);
	public boolean update(Invoice_Detail invoice);
	public boolean remove(Invoice_Detail invoice);
	public List<Invoice_Detail> getInvoice_Details(String id);
}

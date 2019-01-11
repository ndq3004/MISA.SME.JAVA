package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "Reason")
public class Reason {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String reasonTypeID;
	
	
	private String journalMemo;
	
	@OneToMany(mappedBy = "reason")
	private Set<PaymentReceipt> payments;

	public Reason(String reasonTypeID, String journalMemo, Set<PaymentReceipt> reasonTypeIDs) {
		super();
		this.reasonTypeID = reasonTypeID;
		this.journalMemo = journalMemo;
		this.payments = reasonTypeIDs;
	}

	public Reason() {
		super();
	}
	
	
}

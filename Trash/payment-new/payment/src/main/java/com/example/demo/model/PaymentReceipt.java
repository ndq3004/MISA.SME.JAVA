package com.example.demo.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Payment_Receipt")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PaymentReceipt {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private String refID;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "reasonId")
	private Reason reason;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reftypeId")
	private Ref ref;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "payment",fetch = FetchType.LAZY)
	private Set<Invoice_Detail> invoices;

	@Column(name = "Posted_Date")
	private LocalDate postedDate;

	@Column(name = "Account_ObjectID")
	private int accountObjectID;

	@Column(name = "Account_Object")
	private String accountObject;

	@Column(name = "Total_Amount")
	private double totalAmount;

	@Column(name = "Ref_No_Finance")
	private long refNoFinance;

	@Column(name = "Created_Date")
	private LocalDate createdDate;

	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Ref getRef() {
		return ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}

//	public Set<Invoice_Detail> getInvoices() {
//		return invoices;
//	}
//
//	public void setInvoices(Set<Invoice_Detail> invoices) {
//		this.invoices = invoices;
//	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public int getAccountObjectID() {
		return accountObjectID;
	}

	public void setAccountObjectID(int accountObjectID) {
		this.accountObjectID = accountObjectID;
	}

	public String getAccountObject() {
		return accountObject;
	}

	public void setAccountObject(String accountObject) {
		this.accountObject = accountObject;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getRefNoFinance() {
		return refNoFinance;
	}

	public void setRefNoFinance(long refNoFinance) {
		this.refNoFinance = refNoFinance;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

}

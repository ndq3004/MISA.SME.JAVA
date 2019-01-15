package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "invoice_detail")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Invoice_Detail {

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "ref_Type_ID", columnDefinition = "NVARCHAR(16)", length = 16)
	private String refDetailID;

	@Column(name = "description_invoice", columnDefinition = "NVARCHAR(255)")
	private String descriptionInvoice;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "amountOC")
	private Double amountOC;

	@Column(name = "account_object_id")
	private int accountObjectID;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "refId")
	private PaymentReceipt payment;

	public String getRefDetailID() {
		return refDetailID;
	}

	public void setRefDetailID(String refDetailID) {
		this.refDetailID = refDetailID;
	}

	public String getDescriptionInvoice() {
		return descriptionInvoice;
	}

	public void setDescriptionInvoice(String descriptionInvoice) {
		this.descriptionInvoice = descriptionInvoice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountOC() {
		return amountOC;
	}

	public void setAmountOC(Double amountOC) {
		this.amountOC = amountOC;
	}

	public int getAccountObjectID() {
		return accountObjectID;
	}

	public void setAccountObjectID(int accountObjectID) {
		this.accountObjectID = accountObjectID;
	}

	public Invoice_Detail(String refDetailID, String descriptionInvoice, Double amount, Double amountOC,
			int accountObjectID) {
		super();
		this.refDetailID = refDetailID;
		this.descriptionInvoice = descriptionInvoice;
		this.amount = amount;
		this.amountOC = amountOC;
		this.accountObjectID = accountObjectID;
	}

	public Invoice_Detail() {
		super();
	}

}
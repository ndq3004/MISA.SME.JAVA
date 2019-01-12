package com.example.demo.model;

import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "Payment_Receipt")
public class PaymentReceipt {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String refID;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="resonId")
	private Reason reason;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="reftypeId")
	private Ref ref;
	
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "payment")
	private Set<Invoice_Detail> invoices;
	
	@NotNull
	@Column(name = "Posted_Date")
	private Date postedDate;
	
	@NotNull
	@Column(name = "Account_ObjectID")
	private String accountObjectID;
	
	@NotNull
	@Column(name = "Account_Object")
	private String accountObject;
	
	@NotNull
	@Column(name = "Total_Amount")
	private double totalAmount;
	
	@NotNull
	@Column(name = "Ref_No_Finance")
	private long refNoFinance;
	
	@NotNull
	@Column(name = "Created_Date")
	private Date createdDate;


	
	
}

package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Ref")
public class Ref {

	@Id
	 @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	 @GeneratedValue(generator = "generator")
	 @Column(name = "ref_Type_ID" , columnDefinition="NVARCHAR(16)",length=16)
	private String refTypeID;
	
	@Column(name="ref_type",columnDefinition = "NVARCHAR(255)")
	private String refType;
	
	@OneToMany(mappedBy = "ref")
	private Set<PaymentReceipt> payments;

	public String getRefTypeID() {
		return refTypeID;
	}

	public void setRefTypeID(String refTypeID) {
		this.refTypeID = refTypeID;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}
	
	
}
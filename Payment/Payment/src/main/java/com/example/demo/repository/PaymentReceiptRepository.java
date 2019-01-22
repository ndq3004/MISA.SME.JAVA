package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.databaseconfig.SimpleCorsFilter;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;

@Repository
public class PaymentReceiptRepository {
	
	

	public PaymentReceipt getPaymentReceiptById(String id) {
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		PaymentReceipt payment=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			 payment=session.get(PaymentReceipt.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return payment;
	}
	
	public List<PaymentReceipt> getAll(){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		List<PaymentReceipt> lst=new ArrayList<>();
		try {
			//	tx = session.beginTransaction();
			lst = session.createQuery("FROM PaymentReceipt").list(); 
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	public List<PaymentReceipt> getPaymentReceiptByRefType(int refTypeID){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		List<PaymentReceipt> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "SELECT * FROM paymentreceipt WHERE refTypeID = :refTypeID";
			lst= session.createSQLQuery(sql).addEntity(PaymentReceipt.class).setParameter("refTypeID", refTypeID).getResultList();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	public List<PaymentReceipt> getPaymentReceiptOfCompany(String keyCompany){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		List<PaymentReceipt> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "SELECT * FROM paymentreceipt WHERE keyCompany = :keyCompany";
			lst= session.createSQLQuery(sql).addEntity(PaymentReceipt.class).setParameter("keyCompany", keyCompany).getResultList();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	
	public long count(String keyCompany) {
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		long result=0;
		try {
			 tx = session.beginTransaction();
			Object  obj= (Object) session.createQuery("select count(refID) from PaymentReceipt where keyCompany=:keyCompany")
					.setParameter("keyCompany", keyCompany).uniqueResult(); 
			if(obj!=null)result=((Long)obj).longValue();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return result;
	}
	
	public List<PaymentReceipt> getPaymentReceiptOfCompanyPage(String keyCompany,long index,int size){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		List<PaymentReceipt> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "FROM PaymentReceipt WHERE keyCompany = :keyCompany order by createdDate desc";
			lst= session.createQuery(sql)
					.setParameter("keyCompany", keyCompany)
					.setFirstResult((int)index)
					.setMaxResults(size)
					.list();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	
//	public int  update(PaymentReceipt paymentReceipt) {
//
//		Session session=SimpleCorsFilter.sessionFactory.openSession();
//		Transaction tx=null;
//		int result=0;
//		try {
//			 tx = session.beginTransaction();
//			 session.update("PaymentReceipt", paymentReceipt);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx!=null) tx.rollback();
//			e.printStackTrace();
//			 session.close();
//			 return 0;
//		}
//		  
//		 session.close();
//		 return 1;
//	}
	
	
//	public int  save(PaymentReceipt paymentReceipt) {
//
//		Session session=SimpleCorsFilter.sessionFactory.openSession();
//		Transaction tx=null;
//
//		try {
//			 tx = session.beginTransaction();
//			 session.save(paymentReceipt);
//		
//			tx.commit();
//		} catch (Exception e) {
//			if (tx!=null) tx.rollback();
//			e.printStackTrace();
//			 session.close();
//			 return 0;
//		}
//		 session.close();
//		 return 1;
//	}
	public int  save(PaymentReceipt paymentReceipt) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;

		try {
			 tx = session.beginTransaction();
			 if(paymentReceipt.getInvoices()!=null) {
				 //luu paymentReceipt
				 UUID uuid=UUID.randomUUID();
				 String refID=uuid.toString();
				 System.out.println(refID);
				 paymentReceipt.setRefID(refID);
				 session.save(paymentReceipt);
				 List<InvoiceDetail> invoices=paymentReceipt.getInvoices();
				 for(InvoiceDetail invoice:invoices) {
					 //thuc hien luu
					 StringBuffer buffer=new StringBuffer("INSERT INTO table_name ( discription, amountOC,amount,accountObjectID,sortOrder,status,refID)");
					 buffer.append("values( :discription, :amountOC,:amount,:accountObjectID,:sortOrder,:status,:refID)");
					 session.createQuery(buffer.toString())
					 .setParameter("refID", paymentReceipt.getRefID())
					 .setParameter("discription", invoice.getDiscription())
					 .setParameter("amountOC", invoice.getAmountOC())
					 .setParameter("amount", invoice.getAmount())
					 .setParameter("accountObjectID", invoice.getAccountObjectID())
					 .setParameter("status", invoice.getStatus())
					 .setParameter("sortOrder", invoice.getSortOrder())
					 .executeUpdate(); 
				 } 
			 }
			 
			 session.save(paymentReceipt);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		 session.close();
		 return 1;
	}
	
	
	
	public int  update(PaymentReceipt paymentReceipt) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			 
			 //phai xoa ca lien ket nua
			 session.createQuery("delete from InvoiceDetail where payment.refID=:refID")
			 .setParameter("refID", paymentReceipt.getRefID()).executeUpdate(); 
			 
			 if(paymentReceipt.getInvoices()!=null) {
				 //luu invoice
				 List<InvoiceDetail> invoices=paymentReceipt.getInvoices();
				 for(InvoiceDetail invoice:invoices) {
					 session.save(invoice);
				 }
			 }
			 session.update("PaymentReceipt", paymentReceipt);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		  
		 session.close();
		 return 1;
	}
	
	public int  delete(PaymentReceipt paymentReceipt) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		
		try {
			 tx = session.beginTransaction();
			 //phai xoa ca lien ket nua
			 session.createQuery("delete from InvoiceDetail where payment.refID=:refID")
			 .setParameter("refID", paymentReceipt.getRefID()).executeUpdate(); 
			 session.delete(paymentReceipt);
			
			 tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		  session.close(); 
		return 1;
	}
	
	
}

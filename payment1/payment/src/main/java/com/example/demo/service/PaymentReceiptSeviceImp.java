package com.example.demo.service;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.Reason;
import com.example.demo.model.Ref;
import com.example.demo.repository.PaymentReceiptRepository;

@Service
public class PaymentReceiptSeviceImp implements PaymentReceiptService {

	@Autowired
	PaymentReceiptRepository paymentReceiptRepository;
	@Autowired
	RefService refService;

	@Override
	public void savePayment() {
//		List<String> companyName= Arrays.asList("C");

		Scanner s;
		try {
			s = new Scanner(new File("D:/nvlam/payment/company.txt"));
			ArrayList<String> companyname = new ArrayList<String>();
			while (s.hasNextLine()) {
				companyname.add(s.nextLine());
			}
			s.close();
			Random rand = new Random();
			for (int i = 1; i < 100; i++) {
				PaymentReceipt pay = new PaymentReceipt();
				Reason rs = new Reason();
				pay.setRefID(UUID.randomUUID().toString());
				pay.setCreatedDate(LocalDate.now());
				pay.setPostedDate(LocalDate.now());
				String nameOfCompany = companyname.get(rand.nextInt(companyname.size()));
				pay.setAccountObject(nameOfCompany);
				Ref rf = refService.findByRefTypeID(rand.nextInt(9));
				pay.setRef(rf);
				pay.setRefNoFinance(rand.nextInt(10000));
				pay.setTotalAmount(Math.random()*23000);
				pay.setAccountObjectID(rand.nextInt(178));
				rs.setReasonTypeID(rand.nextInt(9));
				if (rf.getRefTypeID() < 4) {
					rs.setJournalMemo("Thu tiền tháng " + rf.getRefTypeID()+1);
				} else {
					rs.setJournalMemo("Chi tiền tháng " + rf.getRefTypeID()+1);
				}
				pay.setReason(rs);
				paymentReceiptRepository.save(pay);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		// System.out.println();
//	}
	}

	@Override
	public List<PaymentReceipt> getAll() {
		return paymentReceiptRepository.findAll();
	}

	@Override
	public List<PaymentReceipt> getPage(long index, int size) {

		return paymentReceiptRepository.getpage(index, size);
	}

	@Override
	public long countTotalRecord() {
		// TODO Auto-generated method stub
		return paymentReceiptRepository.count();
	}
}

package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.Reason;
import com.example.demo.model.Ref;
import com.example.demo.repository.RefRepository;

@Service
public class RefServiceImp implements RefService {

	@Autowired
	RefRepository refRepository;

	@Override
	public void saveRef() {

		Scanner s;
		try {
			s = new Scanner(new File("D:/Quan-SME/Authen-register/RefType.txt"));
			ArrayList<String> reftype = new ArrayList<String>();
			while (s.hasNextLine()) {
				reftype.add(s.nextLine());
			}
			s.close();
			for (int i = 0; i < reftype.size(); i++) {
				Ref ref = new Ref();
				ref.setRefTypeID(i);
				ref.setRefType(reftype.get(i));
				refRepository.save(ref);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
	}
	
	@Override
	public void saveRef(Ref ref) {
		refRepository.save(ref);
	}

	@Override
	public List<Ref> getAll() {
		return null;
	}

	@Override
	public Ref findByRefTypeID(int RefTypeID) {
		// return refRepository.findByRefType(RefTypeID);
		return refRepository.findById(RefTypeID).get();
	}

}

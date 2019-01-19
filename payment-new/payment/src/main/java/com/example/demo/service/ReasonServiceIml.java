package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reason;
import com.example.demo.repository.ReasonRepository;
@Service
public class ReasonServiceIml implements ReasonService {
	@Autowired 
	ReasonRepository  reasonRepository;
	@Override
	public Reason getReasonByType(String journalMemo) {
		Reason reason;
		try {
			reason=reasonRepository.findByJournalMemo(journalMemo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return reason;
	}

}

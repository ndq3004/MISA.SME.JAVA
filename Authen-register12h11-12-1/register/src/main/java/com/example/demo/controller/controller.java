package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.PaymentReceipt;
import com.example.demo.service.PaymentReceiptService;
import com.example.demo.service.RefService;

@RestController
public class controller {

	@Autowired
	PaymentReceiptService payservice;
	@Autowired
	RefService refservice;

	@RequestMapping(value = "/saveData", method = RequestMethod.GET)
	public void save() {
		refservice.saveRef();
		payservice.savePayment();
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<PaymentReceipt> getAll() {
		List<PaymentReceipt> list = payservice.getAll();
		return list;
	}
	
	@RequestMapping(value = "/paypage", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/Views/rae"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value = "add")
	public void addRecord() {
		
	}

//	 @RequestMapping("/getAll")
//	    public Map<String, Object> getAll2() {
//	        int size = 50;
//	        long totalPage = 0;
//	        Map<String, Object> map = new HashMap<String, Object>();
//	        long x =  payservice.countTotalRecord();
//	        if (x == 0) {
//	            map.put("totalPage", totalPage);
//	            map.put("data", null);
//	            return map;}
//	        else if (x % size == 0) {
//	                totalPage = x / size;}
//	            else {
//	                totalPage = x / size + 1;}
//	        List<PaymentReceipt> lst = payservice.getAllList();
//	        map.put("data", lst);
//	        return map;
//	    }
//
//	    @RequestMapping("/getPage/page:{page}_size:{size}")
//	    public List<PaymentReceipt> get(@PathVariable("page") int page,@PathVariable("size") int size) {
//	        long countRecord =  payservice.countTotalRecord();
//	        long index=page*size;
//	        
//	        payservice.getPage(index, size);
//	        
//	        return null;
//	        
//	    }
}

package com.client;


import com.demo.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;

import java.io.StringWriter;
import java.math.BigDecimal;

@RestController
public class MyClientController {
	
	@CloudReference
	private UserService userService;

	public static ObjectMapper mapper = new ObjectMapper();
	public static String toJson(Object obj){
		String json = null;
		try {
			StringWriter writer = new StringWriter();
			JsonGenerator generator = mapper.getFactory().createGenerator(writer);
			mapper.writeValue(generator, obj);
			json = writer.toString();
			generator.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

//	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
//	public String sayHello(){
//
//		return myFirstDemo.sayHello();
//	}
	
//	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
//	public void addUser() {
//		User user = new User();
//		user.setUser_id(1);
//		user.setUser_name("xiao ming");
//		user.setAddress("hangzhou");
//		
//		userService.addUser(user);
//	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser(@RequestParam("cstmr_type")int cstmr_type,
						  @RequestParam("cstmr_name")String cstmr_name,
						  @RequestParam("document_type")int document_type,
						  @RequestParam("document_num")String document_num,
						  @RequestParam("cstmr_id")String cstmr_id)
	{
		User user = new User();
		user.setCstmr_type(cstmr_type);
		user.setCstmr_name(cstmr_name);
		user.setDocument_type(document_type);
		user.setDocument_num(document_num);
		user.setCstmr_id(cstmr_id);
		System.out.println(user);
		return userService.addUser(user);
	}

	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	public String userInfo(@RequestParam("cstmr_id")String cstmr_id)
	{
		//User user = new User();
		//user.cstmr_id = cstmr_id;
		String str = userService.userInfo(cstmr_id);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public String userSearch(@RequestParam("keyword")String keyWord)
	{
		String str = userService.userSearch(keyWord);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/user/fund", method = RequestMethod.GET)
	public String userFund(@RequestParam("cstmr_id")String cstmr_id)
	{
		String str = userService.userFund(cstmr_id);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/user/own", method = RequestMethod.GET)
	public String userOwn(@RequestParam("cstmr_id")String cstmr_id)
	{
		String str = userService.userOwn(cstmr_id);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/user/risk", method = RequestMethod.GET)
	public String userRisk(@RequestParam("cstmr_id")String cstmr_id, @RequestParam("risk_rating")int risk_rating)
	{
		String str = userService.userRisk(cstmr_id, risk_rating);
		System.out.println(str);
		return str;
	}


	@RequestMapping(value = "/getID", method = RequestMethod.GET)
	public String getID()
	{
		long now = System.currentTimeMillis();
		String t = String.valueOf(now);
		return t;
	}

	@RequestMapping(value = "/addCard", method = RequestMethod.GET)
	public String addCard(@RequestParam("credit_id")String credit_id,
						  @RequestParam("cstmr_id")String cstmr_id,
						  @RequestParam(value = "op_type", defaultValue = "1")int op_type)
	{
		Card card = new Card();
		User user = new User();
		card.setCredit_id(credit_id);
		user.setCstmr_id(cstmr_id);
		return userService.addCard(user,card,op_type);
	}

	@RequestMapping(value = "/bankTrade", method = RequestMethod.GET)
	public String bankTrade(@RequestParam("credit_id")String credit_id,
							@RequestParam("cstmr_id")String cstmr_id,
							@RequestParam("trade_type")int trade_type,
							@RequestParam("trade_value")BigDecimal trade_value)
	{
		return userService.bankTrade(credit_id, cstmr_id, trade_type, trade_value);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String userUpdate(@RequestParam("cstmr_type")int cstmr_type,
							@RequestParam("cstmr_id")String cstmr_id,
							@RequestParam("document_type")int document_type,
							@RequestParam("document_num")String document_num,
							 @RequestParam("cstmr_name")String cstmr_name,
							 @RequestParam("risk_rating")int risk_rating)
	{
		User user = new User();
		user.setCstmr_id(cstmr_id);
		user.setCstmr_name(cstmr_name);
		user.setCstmr_type(cstmr_type);
		user.setDocument_type(document_type);
		user.setDocument_num(document_num);
		user.setRisk_rating(risk_rating);
		return userService.userUpdate(user);
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String adminLogin(@RequestParam("name")String name,
							@RequestParam("passwd")String passwd)
	{
		return userService.adminLogin(name,passwd);
	}
}

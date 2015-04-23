package com.excilys.webservice;

import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context-webservice.xml");
		ComputerWebService ws = ctx.getBean(ComputerWebService.class);
		Endpoint.publish("http://localhost:9898/computer-database-ws/computers", ws);
	}
}

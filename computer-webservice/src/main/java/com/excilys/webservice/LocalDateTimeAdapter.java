package com.excilys.webservice;
 
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
 
public class LocalDateTimeAdapter 
    extends XmlAdapter<Timestamp, LocalDateTime>{

	@Override
	public LocalDateTime unmarshal(Timestamp v) throws Exception {
		return v.toLocalDateTime();
	}

	@Override
	public Timestamp marshal(LocalDateTime v) throws Exception {
		return new Timestamp(1000L);
	}
 
}

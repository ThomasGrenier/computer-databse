package com.excilys.webservice;
 
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
 
// TODO: Auto-generated Javadoc
/**
 * The Class LocalDateTimeAdapter.
 */
public class LocalDateTimeAdapter 
    extends XmlAdapter<Timestamp, LocalDateTime>{

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public LocalDateTime unmarshal(Timestamp v) throws Exception {
		return v.toLocalDateTime();
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public Timestamp marshal(LocalDateTime v) throws Exception {
		return new Timestamp(1000L);
	}
 
}

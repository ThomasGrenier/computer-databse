package com.excilys.utils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.EnhancedUserType;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomLocalDateTimeUserType.
 */
public class CustomLocalDateTimeUserType implements EnhancedUserType,
Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant SQL_TYPES. */
	private static final int[] SQL_TYPES = new int[] { Types.TIMESTAMP };

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	
	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return LocalDateTime.class;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		LocalDateTime dtx = (LocalDateTime) x;
		LocalDateTime dty = (LocalDateTime) y;
		return dtx.equals(dty);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	@Override
	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
	 */
	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object timestamp = StandardBasicTypes.TIMESTAMP.nullSafeGet(resultSet,
				names, session, owner);
		if (timestamp == null) {
			return null;
		}
		Date ts = (Date) timestamp;
		Instant instant = Instant.ofEpochMilli(ts.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int, org.hibernate.engine.spi.SessionImplementor)
	 */
	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value,
			int index, SessionImplementor session) throws HibernateException,
			SQLException {
		if (value == null) {
			StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement, null,
					index, session);
		} else {
			LocalDateTime ldt = ((LocalDateTime) value);
			Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
			Date timestamp = Date.from(instant);
			StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement,
					timestamp, index, session);
		}
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#isMutable()
	 */
	@Override
	public boolean isMutable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
	 */
	@Override
	public Object assemble(Serializable cached, Object value)
			throws HibernateException {
		return cached;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.EnhancedUserType#objectToSQLString(java.lang.Object)
	 */
	@Override
	public String objectToSQLString(Object object) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.EnhancedUserType#toXMLString(java.lang.Object)
	 */
	@Override
	public String toXMLString(Object object) {
		return object.toString();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.EnhancedUserType#fromXMLString(java.lang.String)
	 */
	@Override
	public Object fromXMLString(String string) {
		return LocalDateTime.parse(string);
	}

}

package com.duoduoin.springbootreact.dao;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KeyValueObject implements SQLData {

	private String name;
	private String value;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return "KeyValueObject";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {

		name = stream.readString();
		value = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {

		stream.writeString(name);
		stream.writeString(value);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

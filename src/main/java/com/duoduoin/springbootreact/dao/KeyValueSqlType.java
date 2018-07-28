package com.duoduoin.springbootreact.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class KeyValueSqlType extends AbstractSqlTypeValue {

	private KeyValueObject[] keyValueObjects;

	public KeyValueSqlType(KeyValueObject[] keyValueObjects) {
		this.keyValueObjects = keyValueObjects;
	}

	@Override
	protected Object createTypeValue(Connection connection, int sqlType, String typeName) throws SQLException {
		OracleConnection orlConn = null;
		try {
			if (connection.isWrapperFor(OracleConnection.class)) {
				orlConn = connection.unwrap(OracleConnection.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayDescriptor arrayDescriptor = new ArrayDescriptor(typeName, orlConn);
		return new ARRAY(arrayDescriptor, orlConn, keyValueObjects);
	}

}

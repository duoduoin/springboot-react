package com.duoduoin.springbootreact.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import oracle.jdbc.OracleConnection;

public class KeyValueSqlType extends AbstractSqlTypeValue {

	private List<KeyValueObject> keyValueObjects;

	public KeyValueSqlType(List<KeyValueObject> keyValueObjects) {
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
//		Map map = orlConn.getTypeMap();
//		map.put("KEYVALUEOBJECT", KeyValueObject.class);
//		orlConn.setTypeMap(map);
		
		return orlConn.createOracleArray(typeName, keyValueObjects.toArray());
//		ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(typeName, orlConn);
//		return new ARRAY(arrayDescriptor, orlConn, keyValueObjects);
	}

}

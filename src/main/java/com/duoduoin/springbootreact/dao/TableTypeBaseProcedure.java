package com.duoduoin.springbootreact.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.sql.ARRAY;

public class TableTypeBaseProcedure extends StoredProcedure {

	private static final String PROC_NAME = "Sample_Proc";
	private static final String TAB_TYPE = "KEYVALUEOBJECT";

	private static final String MY_ARRAY = "MY_OBJECT_ARRAY";
	private static final String I_ARRAY = "input_array";
	private static final String O_ARRAY = "output_array";

	public TableTypeBaseProcedure(DataSource dataSource) {

		super(dataSource, PROC_NAME);

		declareParameter(new SqlParameter(I_ARRAY, Types.ARRAY, MY_ARRAY));
		declareParameter(new SqlOutParameter(O_ARRAY, Types.ARRAY, MY_ARRAY, new SqlReturnType() {

			@Override
			public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName)
					throws SQLException {
				Connection connection = cs.getConnection();
				Map<String, Class<?>> typeMap = connection.getTypeMap();
				typeMap.put(TAB_TYPE, KeyValueObject.class);
				return cs.getObject(paramIndex);
			}
		}));
		compile();
	}

	public KeyValueObject[] execute(KeyValueObject[] keyValueObjects) {
		Map<String, Object> params = new HashMap<>();
		params.put(I_ARRAY, new KeyValueSqlType(keyValueObjects));

		Map<?, ?> result = execute(params);

		if ((!result.containsKey(O_ARRAY) || result.get(O_ARRAY) == null)) {
			return null;
		}

		try {
			Object[] resultArray = (Object[]) ((ARRAY) result.get(O_ARRAY)).getArray();

			return Arrays.copyOf(resultArray, resultArray.length, KeyValueObject[].class);
		} catch (SQLException e) {
			throw new DataRetrievalFailureException("Unable to retrieve array", e);
		}
	}

}
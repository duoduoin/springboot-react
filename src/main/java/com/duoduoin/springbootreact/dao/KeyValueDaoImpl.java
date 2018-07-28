package com.duoduoin.springbootreact.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KeyValueDaoImpl extends TableTypeBaseProcedure implements KeyValueDao {

	@Autowired
	public KeyValueDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public KeyValueObject[] getKeyValueObjects(KeyValueObject[] keyValueObjects) {

		return execute(keyValueObjects);

	}

}

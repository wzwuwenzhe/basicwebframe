package com.deady.dao;

import com.deady.entity.Operator;

public interface OperatorDAO {

	Operator getOperatorByLoginName(String userName);

}

package com.deady.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deady.dao.OperatorDAO;
import com.deady.entity.Operator;

@Service
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	private OperatorDAO operatorDAO;

	@Override
	public Operator getOperatorByLoginName(String userName) {
		return operatorDAO.getOperatorByLoginName(userName);
	}

}

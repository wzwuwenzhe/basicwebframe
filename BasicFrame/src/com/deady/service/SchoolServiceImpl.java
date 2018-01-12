package com.deady.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deady.dao.SchoolDAO;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDAO schoolDAO;

	@Override
	public List<String> getAllSchoolIds() {
		return schoolDAO.findAllSchoolIds();
	}

}

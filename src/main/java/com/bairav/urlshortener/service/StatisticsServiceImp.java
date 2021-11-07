package com.bairav.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bairav.urlshortener.dao.StatisticsDAO;
import com.bairav.urlshortener.entity.Statistics;

@Service
public class StatisticsServiceImp implements StatisticsService { 
	
	//opens/closes transactions to get data to/from the database via the DAO object
	
	@Autowired
	private StatisticsDAO statisticsDAO;

	@Override
	@Transactional
	public void saveStatistics(Statistics theStatistics) { //save a Statistics object to the statistics table

		statisticsDAO.saveStatistics(theStatistics);

	}

	@Override
	@Transactional
	public Statistics getStatistics(long theId) { //get a statistics entry from the table based from the id of the object
		
		return statisticsDAO.getStatistics(theId);
	}

}

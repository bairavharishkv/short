package com.bairav.urlshortener.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bairav.urlshortener.entity.Statistics;

@Repository
public class StatisticsDAOImp implements StatisticsDAO { //accesses the statistics table in the database
	
	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveStatistics(Statistics theStatistics) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the Statistics entry in the table
		currentSession.saveOrUpdate(theStatistics);
		
	}

	@Override
	public Statistics getStatistics(long theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve/read from database using the primary key
		Statistics theStatistics = currentSession.get(Statistics.class, theId);
		
		return theStatistics;
	}

}

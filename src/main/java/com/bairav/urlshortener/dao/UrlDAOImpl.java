package com.bairav.urlshortener.dao;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bairav.urlshortener.entity.Url;

@Repository
public class UrlDAOImpl implements UrlDAO { //accesses the URL table in the database
	
	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUrl(Url theUrl) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the Url entry in the table
		currentSession.saveOrUpdate(theUrl);

	}

	@Override
	public Url getUrl(long theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve/read from database using the primary key
		Url theUrl = currentSession.get(Url.class, theId);
		
		return theUrl;
	}


	@Override
	public long isValidShortUrl(String shortUrlKey) { //checks to see if the short Url is in the database
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//perform a SELECT query on the url table
		Query<Url> theQuery = currentSession.createQuery("from Url where short_urlkey=:shortUrlKey",Url.class);
		theQuery.setParameter("shortUrlKey", shortUrlKey);
		
		// execute query and get result list
		List<Url> theResult = theQuery.getResultList();

		if(theResult == null  || theResult.size()==0) { //depending upon software versions the resultList is either
														//null or of zero length if nothing is there
			return -1L; 
		}
			
		//if the short URL is valid return its primary key 
		return theResult.get(0).getId();

	}

	@Override
	public long isLongUrlInDB(String theLongUrl) {//checks to see if the long Url is in the database
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		//perform a SELECT query on the url table
		Query<Url> theQuery = currentSession.createQuery("from Url where long_url=:theLongUrl",Url.class);
		theQuery.setParameter("theLongUrl", theLongUrl);
		
		// execute query and get result list
		List<Url> theResult = theQuery.getResultList();

		if(theResult == null || theResult.size()==0) //depending upon software versions the resultList is either
													 //null or of zero length if nothing is there
			return -1L;
		
		//if the long URL is in the database return its primary key
		return theResult.get(0).getId();			
	}

}

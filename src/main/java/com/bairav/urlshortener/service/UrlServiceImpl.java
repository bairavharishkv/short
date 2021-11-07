package com.bairav.urlshortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bairav.urlshortener.converter.Converter;
import com.bairav.urlshortener.dao.UrlDAO;
import com.bairav.urlshortener.entity.Statistics;
import com.bairav.urlshortener.entity.Url;

@Service
public class UrlServiceImpl implements UrlService {
	
	//opens/closes transactions to get data to/from the database via the DAO object
	
	@Autowired
	private UrlDAO urlDAO;

	@Override
	@Transactional
	public void saveUrl(Url theUrl) { //save the Url object as an entry in the url table
		
		urlDAO.saveUrl(theUrl);

	}

	@Override
	@Transactional
	public Url getUrl(long theId) { //overloaded method: get the Url object from its primary key
		
		return urlDAO.getUrl(theId);
	}
	
	@Override
	@Transactional
	public Url getUrl(String uniqueId) { //overloaded method: get the Url object from its short unique-id
		
		//convert the short unique-id back to the primary key value
		Long theId = Converter.getIdFromUniqueKey(uniqueId); 
		
		return urlDAO.getUrl(theId);	
	}

	@Override
	@Transactional
	public List<Statistics> getStatistics(Long theId) { //return the statistics objects associated with the Url object
		
		Url theUrl = getUrl(theId);
		
		return theUrl.getStatistics();
	}

	@Override
	@Transactional
	public long isValidShortUrl(String shortUrlKey) { //returns -1L if not in the database otherwise its primary key
		
		return urlDAO.isValidShortUrl(shortUrlKey);
	}

	@Override
	@Transactional
	public long isLongUrlInDB(String theLongUrl) { //returns -1L if not in the database otherwise its primary key
		
		return urlDAO.isLongUrlInDB(theLongUrl);
	}



}

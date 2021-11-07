package com.bairav.urlshortener.service;

import java.util.List;

import com.bairav.urlshortener.entity.Statistics;
import com.bairav.urlshortener.entity.Url;

public interface UrlService {
	
	public void saveUrl(Url theUrl);

	public Url getUrl(long theId);
	
	public Url getUrl(String uniqueId);

	public List<Statistics> getStatistics(Long theId);
	
	public long isValidShortUrl(String shortUrlKey);
	
	public long isLongUrlInDB(String theLongUrl);

}

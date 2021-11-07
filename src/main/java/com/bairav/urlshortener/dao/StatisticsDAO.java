package com.bairav.urlshortener.dao;

import com.bairav.urlshortener.entity.Statistics;

public interface StatisticsDAO {
	
	public void saveStatistics(Statistics theStatistics);

	public Statistics getStatistics(long theId);
	

}

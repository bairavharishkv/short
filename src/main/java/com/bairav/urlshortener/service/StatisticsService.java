package com.bairav.urlshortener.service;

import com.bairav.urlshortener.entity.Statistics;

public interface StatisticsService {

	public void saveStatistics(Statistics theStatistics);

	public Statistics getStatistics(long theId);
	
}

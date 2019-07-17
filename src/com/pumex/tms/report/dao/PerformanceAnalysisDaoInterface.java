package com.pumex.tms.report.dao;

import java.util.List;

import com.pumex.tms.report.bean.PerformanceAnalysisBean;

/**
 * Service for Performance Analysis
 * 
 * @Author JINSHAD P.T.
 * @Date  31/10/2016
 */

public interface PerformanceAnalysisDaoInterface {

	List getAllTests(long attendeeId) throws Exception;

	List getAllTrainingPrograms(long attendeeId) throws Exception;
	
	PerformanceAnalysisBean getProgramDetails(long attendeeId) throws Exception;
	
	List getFeedBacks(long userId, long topicId) throws Exception;

}

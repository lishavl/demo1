package com.pumex.tms.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.report.bean.PerformanceAnalysisBean;
import com.pumex.tms.report.dao.PerformanceAnalysisDaoInterface;

/**
 * Service for Performance Analysis
 * 
 * @Author JINSHAD P.T.
 * @Date 31/10/2016
 */

@Service
public class PerformanceAnalysisServiceImpl implements
		PerformanceAnalysisServiceInterface {

	@Autowired
	PerformanceAnalysisDaoInterface paDaoInterface;

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public List getAllTests(long attendeeId) throws Exception {

		return paDaoInterface.getAllTests(attendeeId);
	}

	/*
	 * Method to get completed training programs with attended status
	 * 
	 * @return List of completed programs
	 */
	@Override
	public List getAllTrainingPrograms(long attendeeId) throws Exception {

		return paDaoInterface.getAllTrainingPrograms(attendeeId);
	}

	@Override
	public PerformanceAnalysisBean getProgramDetails(long attendeeId)
			throws Exception {

		return paDaoInterface.getProgramDetails(attendeeId);
	}

	@Override
	public List getFeedBacks(long userId, long topicId) throws Exception {

		return paDaoInterface.getFeedBacks(userId, topicId);
	}
}

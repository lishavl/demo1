/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.trainingmanagement.dao.MarkAttendanceDao;

@Service("markattendanceservice")
public class MarkAttendaceServiceImpl implements MarkAttendaceService {

	@Autowired
	MarkAttendanceDao markAttendanceDao;

	
	/*
	 * Method for fetch All Training names
	 * 
	 * @return  list as TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTopicNames(long id) throws Exception {

		return markAttendanceDao.getAllTopicNames(id);
		// TODO Auto-generated method stub
	}
    
	/*
	 * Method for fetch All Attendee names
	 * 
	 * @return  list as TopicAttendeeBean
	 */
	@Override
	public List<TopicAttendeeBean> getAllAttendees(long id) throws Exception {
		// TODO Auto-generated method stub
		return markAttendanceDao.getallAttendees(id);
	}
    
	/*
	 * Method for update attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public void addAttendance(List<Long> id, long testid) throws Exception {

		markAttendanceDao.addAttendance(id, testid);
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelAttendance(long id, long testid) throws Exception {
		markAttendanceDao.cancelAttendance(id, testid);
		// TODO Auto-generated method stub

	}
   
	/*
	 * Method for fetch All Training names
	 * 
	 * @return  list as TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTopicNamesForAdmin()
			throws Exception {
		// TODO Auto-generated method stub
		return markAttendanceDao.getAllTopicNamesForAdmin();
	}


	@Override
	public void cancelTopicCompletionStatus(long testid,long id)
			throws Exception {
		markAttendanceDao.cancelTopicCompletionStatus(testid,id);
		
	}
    
	/*
	 * Method for cancel attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public void CancelAttendanceForAllAttndees(List<Long> list, long testid)
			throws Exception {
		markAttendanceDao.cancelAttendaceForAllAttendees(list,testid);
		// TODO Auto-generated method stub
		
	}
    
	/*
	 * Method for check date for attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public boolean getDateForAttendance(long testid) throws Exception {
		// TODO Auto-generated method stub
		return markAttendanceDao.checkDateForMarkAttendance(testid);
	}

}

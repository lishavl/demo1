/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.usermanagement.bean.Register;

@Transactional
@Repository("TrainingScheduleDao")
public class TrainingScheduleDaoImpl extends AbstractDao<Long, TrainingTopic>
		implements TrainingScheduleDaoInterface {

	@Override
	public List<TrainingTopic> getCategory() throws Exception {
		// TODO Auto-generated method stub
		List list = getSession().createQuery("from TrainingCategory").list();
		return list;
	}

	@Override
	public void scheduleTraining(TrainingTopic trainingtopic,
			long[] trainerlist, long[] attendeelist) throws Exception {
		// TODO Auto-generated method stub
		persist(trainingtopic);

		for (int i = 0; i < trainerlist.length; i++) {
			UserDetails user = new UserDetails();
			user.setUserId(trainerlist[i]);
			
			long userId = trainerlist[i];

			TopicTrainer topictrainer = new TopicTrainer();
			topictrainer.setTopic(trainingtopic);
			topictrainer.setTrainer(user);
			getSession().save(topictrainer);
			
			Notifications notifications = new Notifications();
			notifications.setAdminstatus(0);
			notifications.setStatus(0);
			notifications.setTraining(trainingtopic);
			notifications.setType(1);
			notifications.setUserId(userId);
			getSession().save(notifications);
		}

		for (int i = 0; i < attendeelist.length; i++) {
			UserDetails user = new UserDetails();
			user.setUserId(attendeelist[i]);

			TopicAttendee topicattendee = new TopicAttendee();
			topicattendee.setAttendee(user);
			topicattendee.setTopic(trainingtopic);
			getSession().save(topicattendee);
		}
	}

	@Override
	public long getParentID(String maincategory) throws Exception {
		// TODO Auto-generated method stub
		long pid = (long) getSession()
				.createQuery(
						"select id from TrainingCategory where category = :maincategory and status = 1")
				.setString("maincategory", maincategory).uniqueResult();
		return pid;

	}

	@Override
	public List<Register> FetchTrainers(String text) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where  a.firstName like '%"
								+ text + "%' and b.role.id = 2 and a.status = 1").list();
		return list;
	}

	@Override
	public long saveCategory(String maincategory) throws Exception {
		// TODO Auto-generated method stub

		// System.out.println("Main Category in Dao is"+maincategory);

		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setCategory(maincategory);
		trainingCategory.setStatus(1);
		getSession().save(trainingCategory);

		long categoryId = trainingCategory.getId();
		return categoryId;
	}

	@Override
	public long getCategoryId(String maincategory) throws Exception {
		// TODO Auto-generated method stub

		long cid = (long) getSession()
				.createQuery(
						"select id from TrainingCategory where category = :maincategory and status = 1 ")
				.setString("maincategory", maincategory).uniqueResult();
		return cid;
	}

	@Override
	public long saveSubCategory(String subCategoryName, long categoryid)
			throws Exception {
		// TODO Auto-generated method stub

		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setCategory(subCategoryName);
		trainingCategory.setParentId(categoryid);
		trainingCategory.setStatus(1);
		getSession().save(trainingCategory);

		long categoryId = trainingCategory.getId();
		return categoryId;
	}

	@Override
	public long getTraining(String topic, Timestamp startdate, Timestamp enddate)
			throws Exception {
		// TODO Auto-generated method stub
		long count = (Long) getSession()
				.createQuery(
						"select count(id) from TrainingTopic where topic = :topic and startDateTime =:startdate and endDateTime = :enddate")
				.setString("topic", topic).setTimestamp("startdate", startdate)
				.setTimestamp("enddate", enddate).uniqueResult();

		return count;
	}

	@Override
	public List<TrainingTopic> getAllTrainingSchedules() throws Exception {
		// TODO Auto-generated method stub

		List list = getSession()
				.createQuery(
						"from TrainingTopic where status != 0 order by startDateTime desc")
				.list();
		return list;

	}

	@Override
	public TrainingTopic getScheduleDetails(long id) throws Exception {
		// TODO Auto-generated method stub
		return (TrainingTopic) getSession()
				.createQuery(" from TrainingTopic where id = :id")
				.setLong("id", id).uniqueResult();
	}

	@Override
	public List<Register> getTrainers(long id) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register("
								+ "a.trainer.userId,  a.trainer.firstName,a.trainer.lastName)"
								+ " from TopicTrainer a where a.topic.id = :id")
				.setLong("id", id).list();
		return list;

	}

	@Override
	public List<Register> getAttendee(long id) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register("
								+ "a.attendee.userId,  a.attendee.firstName,a.attendee.lastName)"
								+ " from TopicAttendee a where a.topic.id = :id")
				.setLong("id", id).list();
		return list;
	}

	@Override
	public void deleteSchedule(long id) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" update TrainingTopic  set status = 0 where id = :id")
				.setLong("id", id).executeUpdate();

	}

	@Override
	public void UpdateSchedule(TrainingTopic trainingTopic, long[] trainerlist,
			long[] attendeelist) throws Exception {
		// TODO Auto-generated method stub

		update(trainingTopic);
		long topicid = trainingTopic.getId();

		getSession().createQuery(" delete TopicTrainer where topic.id = :id")
				.setLong("id", topicid).executeUpdate();

		for (int i = 0; i < trainerlist.length; i++) {
			UserDetails user = new UserDetails();
			user.setUserId(trainerlist[i]);

			TopicTrainer topictrainer = new TopicTrainer();
			topictrainer.setTopic(trainingTopic);
			topictrainer.setTrainer(user);
			getSession().save(topictrainer);
		}

		getSession().createQuery(" delete TopicAttendee where topic.id = :id")
				.setLong("id", topicid).executeUpdate();

		for (int i = 0; i < attendeelist.length; i++) {
			UserDetails user = new UserDetails();
			user.setUserId(attendeelist[i]);

			TopicAttendee topicattendee = new TopicAttendee();
			topicattendee.setAttendedStatus(1);
			topicattendee.setAttendee(user);
			topicattendee.setTopic(trainingTopic);
			getSession().save(topicattendee);
		}

	}

	@Override
	public String getMainCategory(long parentId) throws Exception {
		// TODO Auto-generated method stub
		String maincategory = (String) getSession()
				.createQuery(
						"select category from TrainingCategory where id =:parentId and status = 1")
				.setLong("parentId", parentId).uniqueResult();
		return maincategory;
	}

	@Override
	public List<Register> FetchAttendees(String text) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where a.firstName like '%"
								+ text + "%' and b.role.id = 3 and  a.status = 1").list();
		return list;
	}

	@Override
	public List<Register> FetchAttendee(String text) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where b.role.id = 3 and a.firstName like '%"
								+ text + "%' and a.status = 1").list();
		return list;
	}

	@Override
	public List<Register> FetchTrainers(String text, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where b.role.id = 2 and a.firstName like '%"
								+ text + "%' and a.status = 1 and a.userId != :userId").setLong("userId", userId).list();
		return list;
	}

	@Override
	public List<Register> FetchAttendee(String text, long deptid)
			throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where b.role.id = 3 and a.firstName like '%"
								+ text + "%' and a.status = 1 and a.department.id =:deptid").setLong("deptid", deptid).list();
		return list;
	}

}

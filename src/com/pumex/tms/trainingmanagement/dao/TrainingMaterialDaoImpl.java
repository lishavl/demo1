/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.MaterialDocument;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingMaterial;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;

@Transactional
@Repository("TrainingMaterialDao")
public class TrainingMaterialDaoImpl extends
		AbstractDao<Long, TrainingMaterial> implements
		TrainingMaterialDaoInterface {

	@Override
	public List<TrainingMaterialBean> fetchTrainingTopics(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean(a.topic.id ,a.topic.topic)from TopicTrainer a where a.trainer.userId =:trainerid and a.topic.status != 0")
				.setLong("trainerid", trainerid).list();
		return list;
	}

	@Override
	public List<TrainingMaterialBean> fetchTrainings() throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean(a.id ,a.topic)from TrainingTopic a where a.status != 0")
				.list();
		return list;
	}

	@Override
	public void addNewTrainingMaterial(TrainingMaterial material,
			String filepath) throws Exception {
		// TODO Auto-generated method stub

		persist(material);
		long materialid = material.getId();
		MaterialDocument materialDocument = new MaterialDocument();
		materialDocument.setFilepath(filepath);
		materialDocument.setMaterialId(materialid);
		getSession().save(materialDocument);

	}

	@Override
	public List<TrainingMaterialBean> fetchTrainingMaterials(String topic)
			throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean("
								+ "a.id,a.keyword,b.filepath,a.reference,a.description)from TrainingMaterial a, MaterialDocument b "
								+ "where a.id = b.materialId and a.keyword = :topic order by a.id desc")
				.setString("topic", topic).list();
		return list;
	}

	@Override
	public List<TrainingMaterialBean> fetchTrainingMaterialsById(long materialid)
			throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean("
								+ "a.id,a.keyword,b.filepath,a.reference,a.description)from TrainingMaterial a, MaterialDocument b "
								+ "where a.id = b.materialId and a.id = :materialid")
				.setLong("materialid", materialid).list();
		return list;
	}

	@Override
	public void deleteMaterial(long materialid) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" delete MaterialDocument where materialId = :materialid")
				.setLong("materialid", materialid).executeUpdate();

		getSession()
				.createQuery(" delete TrainingMaterial where id = :materialid")
				.setLong("materialid", materialid).executeUpdate();

	}

	@Override
	public String getFileNamel(long materialid) throws Exception {
		// TODO Auto-generated method stub
		String filename = (String) getSession()
				.createQuery(
						" select filepath from  MaterialDocument where materialId = :materialid")
				.setLong("materialid", materialid).uniqueResult();
		return filename;
	}

	@Override
	public TrainingMaterial getMaterialbyId(long materialid) throws Exception {
		// TODO Auto-generated method stub
		return (TrainingMaterial) getSession()
				.createQuery(" from TrainingMaterial where id = :materialid")
				.setLong("materialid", materialid).uniqueResult();
	}

	@Override
	public void updateTrainingMaterial(TrainingMaterial material,
			String filepath) throws Exception {
		// TODO Auto-generated method stub

		update(material);
	}

	@Override
	public void deleteMaterialDoc(long materialid) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" delete MaterialDocument where materialId = :materialid")
				.setLong("materialid", materialid).executeUpdate();

	}

	@Override
	public void saveFile(MaterialDocument materialDocument) throws Exception {
		// TODO Auto-generated method stub
		getSession().save(materialDocument);
	}

}

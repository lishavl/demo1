/*
 * @Author Reshma Manoj
 */
package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.MaterialDocument;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingMaterial;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;
import com.pumex.tms.trainingmanagement.dao.TrainingMaterialDaoInterface;

@Service("materialservice")
public class TrainingMaterialServiceImpl implements
		TrainingMaterialServiceInterface {

	@Autowired
	TrainingMaterialDaoInterface trainingMaterialDaoInterface;

	@Override
	public List<TrainingMaterialBean> fetchTrainingTopics(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingMaterialDaoInterface.fetchTrainingTopics(trainerid);
	}

	@Override
	public List<TrainingMaterialBean> fetchTrainings()
			throws Exception {
		// TODO Auto-generated method stub
		return trainingMaterialDaoInterface.fetchTrainings();
	}

	@Override
	public void addNewTrainingMaterial(TrainingMaterialBean trainingMaterial)
			throws Exception {
		// TODO Auto-generated method stub
		TrainingMaterial material = new TrainingMaterial();
		material.setDescription(trainingMaterial.getDescription());
		material.setKeyword(trainingMaterial.getTopic());
		material.setReference(trainingMaterial.getReference());
		String filepath = trainingMaterial.getFilepath();
		trainingMaterialDaoInterface.addNewTrainingMaterial(material, filepath);
	}

	@Override
	public List<TrainingMaterialBean> fetchTrainingMaterials(String topic)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingMaterialDaoInterface.fetchTrainingMaterials(topic);
	}

	@Override
	public List<TrainingMaterialBean> fetchTrainingMaterialsById(long materialid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingMaterialDaoInterface
				.fetchTrainingMaterialsById(materialid);
	}

	@Override
	public void deleteMaterial(long materialid) throws Exception {
		// TODO Auto-generated method stub
		trainingMaterialDaoInterface.deleteMaterial(materialid);
	}

	@Override
	public String getFileName(long materialid) throws Exception {
		// TODO Auto-generated method stub
		return trainingMaterialDaoInterface.getFileNamel(materialid);
	}

	@Override
	public void updateTrainingMaterial(long materialid,
			TrainingMaterialBean trainingMaterial) throws Exception {
		// TODO Auto-generated method stub

		TrainingMaterial material = trainingMaterialDaoInterface
				.getMaterialbyId(materialid);

		material.setDescription(trainingMaterial.getDescription());
		material.setKeyword(trainingMaterial.getTopic());
		material.setReference(trainingMaterial.getReference());
		String filepath = trainingMaterial.getFilepath();
		trainingMaterialDaoInterface.updateTrainingMaterial(material, filepath);

	}

	@Override
	public void deleteMaterialdoc(long materialid) throws Exception {
		// TODO Auto-generated method stub
		trainingMaterialDaoInterface.deleteMaterialDoc(materialid);
	}

	@Override
	public void saveFile(MaterialDocument materialDocument) throws Exception {
		// TODO Auto-generated method stub

		trainingMaterialDaoInterface.saveFile(materialDocument);

	}

}

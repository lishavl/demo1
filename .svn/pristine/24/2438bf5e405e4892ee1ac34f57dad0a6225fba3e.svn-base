/*
 * @Author Reshma Manoj
 */
package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import com.pumex.tms.models.MaterialDocument;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;

public interface TrainingMaterialServiceInterface {

	List<TrainingMaterialBean> fetchTrainingTopics(long trainerid)
			throws Exception;

	void addNewTrainingMaterial(TrainingMaterialBean trainingMaterial)
			throws Exception;

	List<TrainingMaterialBean> fetchTrainingMaterials(String topic) throws Exception;

	List<TrainingMaterialBean> fetchTrainingMaterialsById(long materialid)
			throws Exception;

	void deleteMaterial(long materialid) throws Exception;

	String getFileName(long materialid) throws Exception;

	void updateTrainingMaterial(long materialid,
			TrainingMaterialBean trainingMaterial) throws Exception;

	void deleteMaterialdoc(long materialid) throws Exception;

	void saveFile(MaterialDocument materialDocument) throws Exception;

	List<TrainingMaterialBean> fetchTrainings() throws Exception;

}

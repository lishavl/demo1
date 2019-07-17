
/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.dao;

import java.util.List;

import com.pumex.tms.models.MaterialDocument;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingMaterial;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;

public interface TrainingMaterialDaoInterface {

	List<TrainingMaterialBean> fetchTrainingTopics(long trainerid) throws Exception;

	void addNewTrainingMaterial(TrainingMaterial material, String filepath) throws Exception;

	List<TrainingMaterialBean> fetchTrainingMaterials(String topic) throws Exception;

	List<TrainingMaterialBean> fetchTrainingMaterialsById(long materialid) throws Exception;

	void deleteMaterial(long materialid) throws Exception;

	String getFileNamel(long materialid) throws Exception;

	TrainingMaterial getMaterialbyId(long materialid) throws Exception;

	void updateTrainingMaterial(TrainingMaterial material, String filepath) throws Exception;

	void deleteMaterialDoc(long materialid) throws Exception;

	void saveFile(MaterialDocument materialDocument) throws Exception;

	List<TrainingMaterialBean> fetchTrainings() throws Exception;

}

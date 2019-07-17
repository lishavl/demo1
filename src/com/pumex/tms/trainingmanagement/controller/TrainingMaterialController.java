/*
 * @Author Reshma Manoj
 */
package com.pumex.tms.trainingmanagement.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pumex.tms.models.MaterialDocument;
import com.pumex.tms.models.Role;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingMaterial;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserRole;
import com.pumex.tms.trainingmanagement.bean.TrainingMaterialBean;
import com.pumex.tms.trainingmanagement.service.TrainingMaterialServiceInterface;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

@Controller
public class TrainingMaterialController {

	@Autowired
	TrainingMaterialServiceInterface trainingMaterialServiceInterface;

	// ------------------Fetch Training Topics -------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/listallscheduledtrainings/", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingMaterialBean>> fetchTopics(
			HttpSession session) throws Exception {
		long trainerid = (long) session.getAttribute("userId");
		long roleId = (long) session.getAttribute("roleId");
		
		if (roleId == 2) {
			List<TrainingMaterialBean> training = trainingMaterialServiceInterface
					.fetchTrainingTopics(trainerid);
			if (training.isEmpty()) {
				return new ResponseEntity<List<TrainingMaterialBean>>(
						HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<TrainingMaterialBean>>(training,
					HttpStatus.OK);
		} else {

			List<TrainingMaterialBean> training = trainingMaterialServiceInterface
					.fetchTrainings();
			if (training.isEmpty()) {
				return new ResponseEntity<List<TrainingMaterialBean>>(
						HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<TrainingMaterialBean>>(training,
					HttpStatus.OK);
		}

	}

	// -------------------ADD NEW Training Material-------------------
	// -----------------------------------------------------------

	@RequestMapping(value = "/saveTrainingMaterial/", method = RequestMethod.POST)
	public @ResponseBody Response addNewTrainingMaterial(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData) throws Exception {

		Gson gson = new Gson();
		TrainingMaterialBean trainingMaterial = gson.fromJson(jsonData,
				TrainingMaterialBean.class);

		if (file != null) {
			// String extension =
			// FilenameUtils.getExtension(file.getOriginalFilename());

			File convFile = new File(AppConstants.FILES_PATH
					+ "trainingmaterials/" + file.getOriginalFilename());
			convFile.createNewFile();

			trainingMaterial.setFilepath(file.getOriginalFilename());

			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		}

		trainingMaterialServiceInterface
				.addNewTrainingMaterial(trainingMaterial);

		return new Response(1, "Add Material Successfull!!!");
	}

	// ------------------Fetch Training Materials & Resources
	// -------------------
	// --------------------------------------------------------------------------

	@RequestMapping(value = "/fetchalltrainingmaterials/{topic}", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingMaterialBean>> fetchMaterials(
			HttpSession session, @PathVariable("topic") String topic)
			throws Exception {

		List<TrainingMaterialBean> training = trainingMaterialServiceInterface
				.fetchTrainingMaterials(topic);
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingMaterialBean>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingMaterialBean>>(training,
				HttpStatus.OK);
	}

	// ------------------Fetch Material By Id-------------------
	// -------------------------------------------------
	@RequestMapping(value = "/fetchtrainingmaterialsbyid/{materialid}", method = RequestMethod.GET)
	public ResponseEntity<List<TrainingMaterialBean>> fetchTrainingMaterials(
			HttpSession session, @PathVariable("materialid") long materialid)
			throws Exception {

		List<TrainingMaterialBean> training = trainingMaterialServiceInterface
				.fetchTrainingMaterialsById(materialid);
		if (training.isEmpty()) {
			return new ResponseEntity<List<TrainingMaterialBean>>(
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TrainingMaterialBean>>(training,
				HttpStatus.OK);
	}

	// ------------------Delete Training Material-------------------
	// ---------------------------------------------------

	@RequestMapping(value = "/deletematerial/{materialid}", method = RequestMethod.GET)
	public @ResponseBody Response deleteMaterials(
			@PathVariable("materialid") long materialid) throws Exception {

		String filepath = trainingMaterialServiceInterface
				.getFileName(materialid);

		trainingMaterialServiceInterface.deleteMaterial(materialid);
		File file = new File(AppConstants.FILES_PATH + "trainingmaterials/"
				+ filepath + ".pdf");
		file.delete();

		return new Response(1,
				"successfully delete category and subcategories!!!!!");
	}

	// -------------------Update Training Material-------------------
	// -----------------------------------------------------------

	@RequestMapping(value = "/updatetrainingmaterial/", method = RequestMethod.POST)
	public @ResponseBody Response updateTrainingMaterial(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String jsonData) throws Exception {

		try {
			Gson gson = new Gson();
			TrainingMaterialBean trainingMaterial = gson.fromJson(jsonData,
					TrainingMaterialBean.class);

			long materialid = trainingMaterial.getMaterial_id();

			if (file != null) {

				String filepath = trainingMaterialServiceInterface
						.getFileName(materialid);

				trainingMaterialServiceInterface.deleteMaterialdoc(materialid);

				File file1 = new File(AppConstants.FILES_PATH
						+ "trainingmaterials/" + filepath);

				file1.delete();

				// String extension =
				// FilenameUtils.getExtension(file.getOriginalFilename());

				File convFile = new File(AppConstants.FILES_PATH
						+ "trainingmaterials/" + file.getOriginalFilename());
				convFile.createNewFile();

				MaterialDocument materialDocument = new MaterialDocument();
				materialDocument.setFilepath(file.getOriginalFilename());
				materialDocument.setMaterialId(trainingMaterial
						.getMaterial_id());
				trainingMaterialServiceInterface.saveFile(materialDocument);

				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(file.getBytes());
				fos.close();
			}

			trainingMaterialServiceInterface.updateTrainingMaterial(materialid,
					trainingMaterial);
		} catch (Exception ex) {

		}
		return new Response(1, "Update Material Successfull!!!");
	}

}

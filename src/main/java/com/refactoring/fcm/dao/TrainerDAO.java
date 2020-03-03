package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.mapper.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * create trainer
 * read trainer
 * revise trainer data
 * delete trainer
 */
@Repository
public class TrainerDAO {

	private TrainerMapper trainerMapper;

	@Autowired
	public void setTrainerMapper(TrainerMapper trainerMapper){
		this.trainerMapper=trainerMapper;
	}

	public TrainerDTO saveTrainer(TrainerDTO trainer){
		trainerMapper.insertTrainer(trainer);
		return trainer;
	}
	public TrainerDTO findTrainerById(String id) {
		return trainerMapper.findTrainerByTrainerId(id);
	}

	public List<TrainerDTO> findAllTrainers(String center_id) {
		List<TrainerDTO> trainers=trainerMapper.selectAllTrainerByCenter_id(center_id);
		return trainers;
	}

	public List<TrainerDTO> findTrainersByName(String name, String center_id){
		List<TrainerDTO> trainers=trainerMapper.selectTrainerByName(name,center_id);
		return trainers;
	}

	public TrainerDTO reviseTrainerData(TrainerDTO trainer){
		trainerMapper.updateTrainer((TrainerDTO)trainer);
		return trainer;
	}

	public TrainerDTO reviseTrainerDataMyManager(TrainerDTO trainer){
		trainerMapper.updateTrainerInfoByManager(trainer);
		return trainer;
	}

	public boolean deleteTrainer(String id){
		trainerMapper.deleteTrainer(id);
		return true;
	}

}

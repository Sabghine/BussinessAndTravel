package pidev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Invitation;
import pidev.spring.entities.Planning;
import pidev.spring.repository.PlanningRepository;

@Service
public class PlanningSeviceImpl implements PlanningService {

	@Autowired
	PlanningRepository pR;
	
	@Override
	public Planning savePlanning(Planning P) {
		return pR.save(P);
	}

	@Override
	public Planning updatePlanning(Planning P) {
		return pR.save(P);
	}

	@Override
	public void deletePlanning(Planning P) {
		pR.delete(P);
	}

	@Override
	public void deletePlanningById(Long id) {
		pR.deleteById(id);
	}

	@Override
	public Planning getPlanning(Long id) {
		return pR.findById(id).get();
	}

	@Override
	public List<Planning> getAllPlannings() {
		return pR.findAll();
	}
	
}

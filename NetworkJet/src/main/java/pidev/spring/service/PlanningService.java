package pidev.spring.service;

import java.util.List;

import pidev.spring.entities.Planning;

public interface PlanningService {
	Planning savePlanning(Planning P);
	Planning updatePlanning(Planning P);
	void deletePlanning(Planning P);
	 void deletePlanningById(Long id);
	 Planning getPlanning(Long id);
	List<Planning> getAllPlannings();
}

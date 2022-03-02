package pidev.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pidev.spring.entities.Invitation;
import pidev.spring.entities.Planning;
import pidev.spring.service.PlanningService;

@RestController
@RequestMapping("/api/planning")
@CrossOrigin
public class PlanningRestController {
	
	@Autowired
	PlanningService planningService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Planning> getAllPlannings() {
	return planningService.getAllPlannings();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Planning getPlanningById(@PathVariable("id") Long id) {
		
	return planningService.getPlanning(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public Planning sendInvitation(@RequestBody Planning planning) {
	return planningService.savePlanning(planning);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Planning updatePlanning(@RequestBody Planning planning) {
	return planningService.updatePlanning(planning);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deletePlanning(@PathVariable("id") Long id)
	{
	planningService.deletePlanningById(id);
	}

}

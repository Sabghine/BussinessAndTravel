package pidev.spring.controller;

import java.util.List;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pidev.spring.entities.Planning;
import pidev.spring.service.PlanningService;

@Controller
public class PlanningController {
	@Autowired
	PlanningService planningService;
	
	@RequestMapping("/createPlanning")
	public String showCreate()
	{
	return "createPlanning";
	}
	
	@RequestMapping ("/savePlanning")
	public String savePlanning(@ModelAttribute("planning") Planning planning, ModelMap modelMap) throws
	ParseException
	{
		Planning savePlanning = planningService.savePlanning(planning);
		String msg ="planning enregistré avec Id "+savePlanning.getIdPlanning();
		modelMap.addAttribute("msg", msg);
		return "createPlanning";
	}
	
	@RequestMapping("/ListePlannings")
	public String listePlannings(ModelMap modelMap)
	{
	List<Planning> plans = planningService.getAllPlannings();
	modelMap.addAttribute("plannings", plans);
	return "listePlannings";
	}
	
	@RequestMapping("/supprimerPlanning")
	public String supprimerPlanning(@RequestParam("id") Long id,
	 ModelMap modelMap)
	{ 
	planningService.deletePlanningById(id);
	List<Planning> plans = planningService.getAllPlannings();
	modelMap.addAttribute("plannings", plans);
	return "listePlannings";
	}

	
	

}

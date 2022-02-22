package pidev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import pidev.spring.service.IComplaint;

public class ComplaintRestController {
	@Autowired 
	IComplaint complaintService;

}

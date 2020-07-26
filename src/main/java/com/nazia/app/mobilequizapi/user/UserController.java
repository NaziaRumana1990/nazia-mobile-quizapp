package com.nazia.app.mobilequizapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nazia.app.mobilequizapi.model.ApplicationUser;
import com.nazia.app.mobilequizapi.services.QuestionAnswerService;

@RestController
@RequestMapping("/users")

public class UserController {
	
	  @Autowired QuestionAnswerService questionAnswerService;
	  
	  @PostMapping("/sign-up") public void signUp(@RequestBody ApplicationUser
	  user) { questionAnswerService.saveUser(user); }
	 
}
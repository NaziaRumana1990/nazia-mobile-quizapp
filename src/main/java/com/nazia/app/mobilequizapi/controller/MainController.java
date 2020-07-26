package com.nazia.app.mobilequizapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nazia.app.mobilequizapi.model.ApplicationUser;
import com.nazia.app.mobilequizapi.model.Game;
import com.nazia.app.mobilequizapi.model.NewGameRequestData;
import com.nazia.app.mobilequizapi.model.QuestionAnswers;
import com.nazia.app.mobilequizapi.model.ResponseBodyMessage;
import com.nazia.app.mobilequizapi.services.QuestionAnswerService;

@Controller
public class MainController {
	
	@Autowired
	QuestionAnswerService questionAnswerService;
	
	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
	   	questionAnswerService.saveUser(user);
	}
	
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<QuestionAnswers>> getAllQuestions() {
		List<QuestionAnswers> questionAnswerList = new ArrayList<QuestionAnswers>(questionAnswerService.getQuestionanswers());
		return new ResponseEntity<List<QuestionAnswers>>(questionAnswerList,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/answers")
	@ResponseBody
	ResponseBodyMessage submitAnswers(@RequestBody List<NewGameRequestData> requestData) {
		ResponseBodyMessage responseData = questionAnswerService.submitAnswers(requestData);
		return responseData;
	}
	
	@RequestMapping(value = "/statistics/{gameID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Game>> retrieveGameStatistics(@PathVariable("gameID") int gameID) {
		List<Game> resultantList = questionAnswerService.retrieveGameStatistics(gameID);
		return new ResponseEntity<List<Game>>(resultantList,HttpStatus.OK);
	}
	
}

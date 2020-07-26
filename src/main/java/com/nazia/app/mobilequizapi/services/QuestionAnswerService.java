package com.nazia.app.mobilequizapi.services;

import java.util.List;

import com.nazia.app.mobilequizapi.model.Game;
import com.nazia.app.mobilequizapi.model.NewGameRequestData;
import com.nazia.app.mobilequizapi.model.QuestionAnswers;
import com.nazia.app.mobilequizapi.model.ResponseBodyMessage;
import com.nazia.app.mobilequizapi.model.ApplicationUser;

public interface QuestionAnswerService {
	public List<QuestionAnswers> getQuestionanswers();
	public ResponseBodyMessage submitAnswers(List<NewGameRequestData> requestData);
	public List<Game> retrieveGameStatistics(int gameID);
	public void saveUser(ApplicationUser user);
}

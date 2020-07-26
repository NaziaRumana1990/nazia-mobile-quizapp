package com.nazia.app.mobilequizapi.dao;

import java.util.List;

import com.nazia.app.mobilequizapi.model.Answer;
import com.nazia.app.mobilequizapi.model.Game;
import com.nazia.app.mobilequizapi.model.NewGameRequestData;
import com.nazia.app.mobilequizapi.model.Questions;
import com.nazia.app.mobilequizapi.model.ApplicationUser;

public interface QuestionAnswerDao {

	List<Questions> getQuestionsFromDB();
	List<Answer> getAnswersFromDB();
	boolean submitAnswerstoDB(List<NewGameRequestData> dataFrmReq);
	int fetchCorrectAnswerFromDB(int questionID);
	void updateValidatedAnswerinDB(int questionID, boolean isCorrectAnswer, int gameID);
	List<Game> retrieveStatisticsFromDB(int gameID);
	void saveUserinDB(ApplicationUser user);
	ApplicationUser findByUsername(String username);
}

package com.nazia.app.mobilequizapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.nazia.app.mobilequizapi.model.QuestionAnswers;
import com.nazia.app.mobilequizapi.model.Questions;
import com.nazia.app.mobilequizapi.model.ResponseBodyMessage;
import com.nazia.app.mobilequizapi.dao.QuestionAnswerDao;
import com.nazia.app.mobilequizapi.model.Answer;
import com.nazia.app.mobilequizapi.model.Game;
import com.nazia.app.mobilequizapi.model.NewGameRequestData;
import com.nazia.app.mobilequizapi.model.ApplicationUser;

@Service("QuestionAnswerService")
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

	@Autowired
	QuestionAnswerDao questionAnswerDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<QuestionAnswers> getQuestionanswers() {
		List<Questions> questionList = questionAnswerDao.getQuestionsFromDB();
		List<Answer> answerList = questionAnswerDao.getAnswersFromDB();
		List<QuestionAnswers> questionAnswerList = new ArrayList<QuestionAnswers>();
		for (Questions questions : questionList) {
			QuestionAnswers qa = new QuestionAnswers();
			for (Answer answer : answerList) {
				if (answer.getQuestion_id() == questions.getId()) {
					qa.setQuestion_id(questions.getId());
					qa.setQuestion(questions.getQvalue());
					qa.setOption1(answer.getOption_1());
					qa.setOption2(answer.getOption_2());
					qa.setOption3(answer.getOption_3());
					qa.setOption4(answer.getOption_4());
					questionAnswerList.add(qa);
					}
				}
			}
		return questionAnswerList;
	}

	@Override
	public ResponseBodyMessage submitAnswers(List<NewGameRequestData> dataFrmReqList) {
		ResponseBodyMessage responseData = new ResponseBodyMessage();
		 boolean submitAnswers = questionAnswerDao.submitAnswerstoDB(dataFrmReqList);
		 boolean isCorrectAnswer;
		 if (submitAnswers) {
			  responseData.setStringCode("200");
			  responseData.setStringMessage("Answer submitted successfully");
			  for(NewGameRequestData gameData: dataFrmReqList) {
				  int correctOption = questionAnswerDao.fetchCorrectAnswerFromDB(gameData.getQuestionID());
				  isCorrectAnswer = false;
				if(gameData.getOptionNo() == correctOption)
					isCorrectAnswer = true;
				questionAnswerDao.updateValidatedAnswerinDB(gameData.getQuestionID(), isCorrectAnswer, gameData.getGameID());
			  }	  
		  }
		return responseData;
	}

	@Override
	public List<Game> retrieveGameStatistics(int gameID) {
		List<Game> statReport = questionAnswerDao.retrieveStatisticsFromDB(gameID);
		return statReport;
	}
	
	@Override
	public void saveUser(ApplicationUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		questionAnswerDao.saveUserinDB(user);
	}
	
}

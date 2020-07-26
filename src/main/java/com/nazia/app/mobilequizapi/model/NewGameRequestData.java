package com.nazia.app.mobilequizapi.model;

public class NewGameRequestData {

	private int gameID;
	private int questionID;
	private int optionNo;
	private boolean islifeLineUsed;
	private int answeredTimeInSeconds;
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public boolean isIslifeLineUsed() {
		return islifeLineUsed;
	}
	public void setIslifeLineUsed(boolean islifeLineUsed) {
		this.islifeLineUsed = islifeLineUsed;
	}
	public int getAnsweredTimeInSeconds() {
		return answeredTimeInSeconds;
	}
	public void setAnsweredTimeInSeconds(int answeredTimeInSeconds) {
		this.answeredTimeInSeconds = answeredTimeInSeconds;
	}
	
}

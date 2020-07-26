package com.nazia.app.mobilequizapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="game")
public class Game {
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="user_id")
	private int user_id;
	@Column(name="game_id")
	private int game_id;
	@Column(name="question_id")
	private int question_id;
	@Column(name="optionNo")
	private int optionNo;
	@Column(name="isanswercorrect")
	private boolean isanswercorrect;
	@Column(name="islifelineused")
	private boolean islifelineused;
	@Column(name="answeredTimeInSeconds")
	private int answeredTimeInSeconds;
	
	public int getAnsweredTimeInSeconds() {
		return answeredTimeInSeconds;
	}
	public void setAnsweredTimeInSeconds(int answeredTimeInSeconds) {
		this.answeredTimeInSeconds = answeredTimeInSeconds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public boolean isIsanswercorrect() {
		return isanswercorrect;
	}
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public void setIsanswercorrect(boolean isanswercorrect) {
		this.isanswercorrect = isanswercorrect;
	}
	public boolean isIslifelineused() {
		return islifelineused;
	}
	public void setIslifelineused(boolean islifelineused) {
		this.islifelineused = islifelineused;
	}
		
}


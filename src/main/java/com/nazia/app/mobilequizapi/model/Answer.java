package com.nazia.app.mobilequizapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer {
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name="question_id")
	private int question_id;
	
	@Column(name="option_1")
	private String option_1;
	
	@Column(name="option_2")
	private String option_2;
	
	@Column(name="option_3")
	private String option_3;
	
	@Column(name="option_4")
	private String option_4;
	
	@Column(name="correctanswer")
	private int correctanswer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getOption_1() {
		return option_1;
	}
	public void setOption_1(String option_1) {
		this.option_1 = option_1;
	}
	public String getOption_2() {
		return option_2;
	}
	public void setOption_2(String option_2) {
		this.option_2 = option_2;
	}
	public String getOption_3() {
		return option_3;
	}
	public void setOption_3(String option_3) {
		this.option_3 = option_3;
	}
	public String getOption_4() {
		return option_4;
	}
	public void setOption_4(String option_4) {
		this.option_4 = option_4;
	}
	public int getCorrectanswer() {
		return correctanswer;
	}
	public void setCorrectanswer(int correctanswer) {
		this.correctanswer = correctanswer;
	}
	
}

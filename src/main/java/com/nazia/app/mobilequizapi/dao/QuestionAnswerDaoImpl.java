package com.nazia.app.mobilequizapi.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nazia.app.mobilequizapi.model.Answer;
import com.nazia.app.mobilequizapi.model.Game;
import com.nazia.app.mobilequizapi.model.NewGameRequestData;
import com.nazia.app.mobilequizapi.model.Questions;
import com.nazia.app.mobilequizapi.model.ApplicationUser;

@Repository
@Transactional(rollbackOn = Exception.class)
public class QuestionAnswerDaoImpl implements QuestionAnswerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Questions> getQuestionsFromDB() {	
		Session s1= this.sessionFactory.openSession();
		List<Questions> questList =  s1.createQuery("from Questions").list();
		s1.close();
		return questList;
	}

	@Override
	public List<Answer> getAnswersFromDB() {
		Session s2= this.sessionFactory.openSession();
		List<Answer> ansList = s2.createQuery("from Answer").list();
		s2.close();
		return ansList;
	}

	@Override
	public boolean submitAnswerstoDB(List<NewGameRequestData> dataFrmReqList) {
		boolean isTxSuccess = false;
		Session session = this.sessionFactory.openSession();
        try {
        	
            for (int i = 0; i < dataFrmReqList.size(); i++) {
                NewGameRequestData dataFrmReq = dataFrmReqList.get(i);
                Game game = new Game();
                session.beginTransaction();
                game.setGame_id(dataFrmReq.getGameID());
    			game.setQuestion_id(dataFrmReq.getQuestionID());
    			game.setOptionNo(dataFrmReq.getOptionNo());
    			game.setIslifelineused(dataFrmReq.isIslifeLineUsed());
                session.save(game);
                session.getTransaction().commit();
            }
            isTxSuccess = true;
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
            	session.getTransaction().rollback();
            e.printStackTrace();
            isTxSuccess = false;
        } finally {
            session.close();
        }
		return isTxSuccess;
	}

	@Override
	public int fetchCorrectAnswerFromDB(int questionID) {
		Session session= this.sessionFactory.openSession();
		Query query = session.createQuery(" from Answer a where a.question_id = :questId");
		query.setParameter("questId", questionID);
		Answer resultData = (Answer) query.getSingleResult();
		int correctAnswer = resultData.getCorrectanswer();
		session.close();
		return correctAnswer;
	}

	@Override
	public void updateValidatedAnswerinDB(int questionID, boolean isCorrectAnswer, int gameID) {
		System.out.println("Inside DAO:updateValidatedAnswerinDB where GameID :: "+ gameID +" and questionID ::"+ questionID + "-----" +isCorrectAnswer);
		Session session = this.sessionFactory.openSession();
		session.clear();
		String hql = "update Game set isanswercorrect =:isCorrectParam where "
				+ " question_id = :questionID and game_id = :gameID";
		try{
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("questionID", questionID);
			query.setParameter("isCorrectParam", isCorrectAnswer);
			query.setParameter("gameID",gameID);
			query.executeUpdate();
			session.getTransaction().commit();
			}
			catch (HibernateException e) {
	            if (session.getTransaction() != null)
	            	session.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }		
	}

	@Override
	public List<Game> retrieveStatisticsFromDB(int gameID) {
		Session session= this.sessionFactory.openSession();	
		Query query=  session.createQuery("from Game g where g.game_id = :gameID");
		query.setParameter("gameID",gameID);
		List<Game> gameValues = query.getResultList();
		session.close();
		return gameValues;
	}
	
	@Override
	public void saveUserinDB(ApplicationUser userData) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
        session.save(userData);
        session.getTransaction().commit();
        session.close();
	}
	
	@Override
	public ApplicationUser findByUsername(String username) {
        Session session= this.sessionFactory.openSession();
        Query query = session.createQuery("from ApplicationUser where username = :uname").setParameter("uname", username);
        ApplicationUser userDetails = (ApplicationUser) query.getResultList().get(0);
		session.close();
		return userDetails; 
	}
	
}

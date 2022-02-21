package ru.itmo.bussiness_logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.bussiness_logic.dao.QuestionDao;
import ru.itmo.bussiness_logic.dto.QuestionDto;
import ru.itmo.bussiness_logic.entities.Question;
import ru.itmo.bussiness_logic.enums.Tag;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public QuestionDto addQuestion(QuestionDto questionDto){
        try {
            return questionDao.save(new Question(questionDto.getCreatorId(), questionDto.getHead(), questionDto.getBody(), false, Tag.valueOf(questionDto.getTag())), questionDto.getToken());
        }
        catch (NullPointerException e){
            return new QuestionDto("Bad request missing some of the parameters");
        }
    }

    public QuestionDto getForUser(int userID){
        QuestionDto questionDto =  questionDao.getQuestionsByUserId(userID);
        if(questionDto.getMsg()==null){
            questionDto.setMsg("Failed to find user with that id: " + userID);
            return questionDto;
        }
        questionDto.setMsg("Success");
        return questionDto;
    }



    public QuestionDto changeQuestionStatus(Integer questionId, boolean isValid) {
        if(questionId==null)
        {
            return new QuestionDto("Bad request no id found");
        }
        if(!isValid) {
            return questionDao.deleteQuestion(questionId);
        }
        return questionDao.changeQuestionStatusToEvaluated(questionId);
    }


    public QuestionDto deleteQuestion(Integer questionId, String token){
        if(questionId==null)
        {
            return new QuestionDto("Bad request no id found");
        }
        return questionDao.deleteQuestion(questionId, token);

    }

    public QuestionDto getAll(){
        return questionDao.getAllQuestions();
    }



}



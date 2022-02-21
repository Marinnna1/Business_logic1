package ru.itmo.bussiness_logic.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.bussiness_logic.dto.QuestionDto;
import ru.itmo.bussiness_logic.services.QuestionService;


@RestController
@RequestMapping("questions/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PutMapping("add")
    public QuestionDto addQuestion(@RequestBody QuestionDto question){
        return questionService.addQuestion(question);
    }


    @PostMapping("change_status")
    public QuestionDto changeQuestionStatus(@RequestBody QuestionDto questionDto) {
        return questionService.changeQuestionStatus(questionDto.getId(), questionDto.getEvaluated());
    }




    @GetMapping(path = "user/{userId}")
    public QuestionDto getForUser(@PathVariable int userId) {
        return questionService.getForUser(userId);
    }



    @DeleteMapping("delete")
    public QuestionDto deleteQuestion(@RequestBody QuestionDto questionDto){
        try {
            return questionService.deleteQuestion(questionDto.getId(), questionDto.getToken());
        }
        catch (Exception e) {
            return new QuestionDto("Bad request: no id");
        }

    }




    @GetMapping("all")
    public QuestionDto getAll() {
        return questionService.getAll();
    }



}



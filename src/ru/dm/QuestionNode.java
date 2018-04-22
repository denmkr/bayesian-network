package ru.dm;

/**
 * Created by denis on 21/04/2018.
 */
public class QuestionNode extends Node {
    private Question question;

    public QuestionNode() {
        super.setType(Type.Question);
    }

    public QuestionNode(Question question) {
        this.question = question;
        super.setType(Type.Question);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

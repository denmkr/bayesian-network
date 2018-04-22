package ru.dm;

/**
 * Created by denis on 21/04/2018.
 */
public class QuestionNode extends Node {
    private Question question;
    private double grade;

    public QuestionNode() {
        super.setType(Type.Question);
    }

    public QuestionNode(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

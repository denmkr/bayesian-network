package ru.dm;

/**
 * Created by denis on 21/04/2018.
 */
public class QuestionNode extends Node {
    private Question question;
    private boolean isChanged = false;

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

    public boolean isChanged() {
        return this.isChanged;
    }

    public void setChanged(boolean value) {
        this.isChanged = value;
    }
}

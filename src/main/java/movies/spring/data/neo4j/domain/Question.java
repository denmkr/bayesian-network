package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 22/04/2018.
 */
public class Question {
    private String text;
    private List<String> answers = new ArrayList<String>();

    public Question(String text, List<String> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }
}

package movies.spring.data.neo4j.domain;

/**
 * Created by denis on 21/04/2018.
 */

public class QuestionNode extends Node {

    private Question question;
    private boolean isChanged = false;

    public boolean isChanged() {
        return this.isChanged;
    }

    public void setChanged(boolean value) {
        this.isChanged = value;
    }
}

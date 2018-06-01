package movies.spring.data.neo4j.views;

/**
 * Created by denis on 31/05/2018.
 */
public class StudentView {
    private String name;
    private double value;
    private double errorValue;
    private double notLearntValue;

    public StudentView() {
    }

    public StudentView(String name, double value, double errorValue) {
        this.name = name;
        this.value = value * 100;
        this.errorValue = errorValue * 100;
        this.notLearntValue = (1 - value - errorValue) * 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(double errorValue) {
        this.errorValue = errorValue;
    }

    public double getNotLearntValue() {
        return notLearntValue;
    }

    public void setNotLearntValue(double notLearntValue) {
        this.notLearntValue = notLearntValue;
    }
}

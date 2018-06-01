package movies.spring.data.neo4j.views;

/**
 * Created by denis on 31/05/2018.
 */
public class CompetenceView {
    private String axis;
    private double value;

    public CompetenceView() {
    }

    public CompetenceView(String axis, double value) {
        this.axis = axis;
        this.value = value;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

package movies.spring.data.neo4j.views;


/**
 * Created by denis on 31/05/2018.
 */
public class TopicView {
    private Long id;
    private String label;
    private double score;
    private Float weight;

    public TopicView() {
    }

    public TopicView(String name) {
        this.label = name;
    }

    public TopicView(Long id, String label, double score) {
        this.id = id;
        this.label = label;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}

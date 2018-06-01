package movies.spring.data.neo4j.views;

/**
 * Created by denis on 31/05/2018.
 */

public class LinkView {

    private Long source;
    private Long target;
    private String type;

    public LinkView() {
    }

    public LinkView(Long source, Long target, String type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
package movies.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

/**
 * Created by denis on 23/05/2018.
 */
@RelationshipEntity(type="Relation")
public class Relation {

    @Id
    @GeneratedValue
    private Long id;

    @Convert(WeightConverter.class)
    private Float weight;

    @StartNode
    private Node startNode;

    @EndNode
    private Node nextNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Relation() {
    }

    public Relation(Node start, Node next, Long id) {
        this.startNode = start;
        this.nextNode = next;
        this.id = id;
    }
}

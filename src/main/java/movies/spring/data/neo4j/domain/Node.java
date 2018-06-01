package movies.spring.data.neo4j.domain;

/**
 * Created by denis on 23/05/2018.
 */
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Node {

    private Question question;
    private boolean isChanged = false;

    public boolean isChanged() {
        return this.isChanged;
    }

    public void setChanged(boolean value) {
        this.isChanged = value;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Convert(TypeConverter.class)
    private Type type;

    private String name;

    @Relationship(type = "Relation", direction = Relationship.OUTGOING)
    private List<Relation> relationships = new ArrayList<>();

    /*
    @Relationship(type = "Relation", direction = Relationship.INCOMING)
    private List<Relation> incomeRelationships = new ArrayList<>();
    */

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Relation> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relation> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(Relation relationship) {
        this.relationships.add(relationship);
    }

    /* */

    private double probability;
    private double notKnownProbability;

    public double getProbability() {
        if (this.probability == 0 && !this.relationships.isEmpty()) {
            double probabilityResult = 0;
            for (Relation relation: this.relationships) {
                Node node = relation.getNextNode();
                probabilityResult += node.getProbability() * relation.getWeight();
            }
            return probabilityResult;
        }
        else return probability;
    }

    public double getNotKnownProbability() {
        if (this.notKnownProbability == 0 && !this.relationships.isEmpty()) {
            double notKnownProbabilityResult = 0;
            for (Relation relation: this.relationships) {
                Node node = relation.getNextNode();
                notKnownProbabilityResult += node.getNotKnownProbability() * relation.getWeight();
            }
            return notKnownProbabilityResult;
        }
        else return notKnownProbability;
    }

    public double getNotLearntProbability() {
        if (this.probability == 0 && !this.relationships.isEmpty()) {
            double notLearntProbabilityResult = 0;
            for (Relation relation: this.relationships) {
                Node node = relation.getNextNode();
                notLearntProbabilityResult += (1 - node.getNotKnownProbability() - node.getProbability()) * relation.getWeight();
            }
            return notLearntProbabilityResult;
        }
        else return (1 - getNotKnownProbability() - getProbability());
    }

    public void setNotKnownProbability(double notKnownProbability) {
        this.notKnownProbability = notKnownProbability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

}
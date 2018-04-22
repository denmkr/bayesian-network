package ru.dm;

/**
 * Created by denis on 21/04/2018.
 */
public class Course {
    private String name;
    private String description;
    private String author;
    private String creationDate;
    private Node competenceNode;

    public Course(String name, Node competenceNode) {
        this.name = name;
        this.competenceNode = competenceNode;
    }

    public Course(String name, String description, String author, Node competenceNode) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.competenceNode = competenceNode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Node getCompetenceNode() {
        return competenceNode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCompetenceNode(Node competenceNode) {
        this.competenceNode = competenceNode;
    }

    public double getCompetenceLevelResult() {
        return this.competenceNode.getProbability();
    }

    public double getCompetenceErrorResult() {
        return this.competenceNode.getNotKnownProbability();
    }

    public double getCompetenceNotLearntResult() {
        return this.competenceNode.getNotLearntProbability();
    }
}

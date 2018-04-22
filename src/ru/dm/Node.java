package ru.dm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 19/04/2018.
 */
public class Node {
    private String name;
    private Type type;
    private List<Node> childNodes = new ArrayList<Node>();
    // private List<Double> weights = new ArrayList<Double>();
    private double probability;
    private double weight;

    private double notKnownProbability;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public Node(String name, double probability, double weight) {
        this.name = name;
        this.probability = probability;
        this.weight = weight;
    }

    public Node(String name, Type type, double probability, double weight) {
        this.name = name;
        this.type = type;
        this.probability = probability;
        this.weight = weight;
    }

    public Node() {
    }

    public double getProbability() {
        if (this.probability == 0 && !this.childNodes.isEmpty()) {
            double probabilityResult = 0;
            for (Node node: childNodes) {
                // childNodes.indexOf(node);
                probabilityResult += node.getProbability() * node.getWeight();
            }
            return probabilityResult;
        }
        else return probability;
    }

    public double getNotKnownProbability() {
        if (this.notKnownProbability == 0 && !this.childNodes.isEmpty()) {
            double notKnownProbabilityResult = 0;
            for (Node node: childNodes) {
                notKnownProbabilityResult += node.getNotKnownProbability() * node.getWeight();
            }
            return notKnownProbabilityResult;
        }
        else return notKnownProbability;
    }

    public void setNotKnownProbability(double notKnownProbability) {
        this.notKnownProbability = notKnownProbability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public void addChildNode(Node childNode) {
        this.childNodes.add(childNode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

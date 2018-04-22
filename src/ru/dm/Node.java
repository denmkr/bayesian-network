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
    private List<Double> weights = new ArrayList<Double>();
    private double probability;

    private double notKnownProbability;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public Node(String name, Type type, double probability) {
        this.name = name;
        this.type = type;
        this.probability = probability;
    }

    public Node(String name, double probability, List<Double> weights) {
        this.name = name;
        this.probability = probability;
        this.weights = weights;
    }

    public Node(String name, Type type, double probability, List<Double> weights) {
        this.name = name;
        this.type = type;
        this.probability = probability;
        this.weights = weights;
    }

    public Node() {
    }

    public double getProbability() {
        if (this.probability == 0 && !this.childNodes.isEmpty()) {
            double probabilityResult = 0;
            for (Node node: childNodes) {
                int weightIndex = childNodes.indexOf(node);
                probabilityResult += node.getProbability() * this.weights.get(weightIndex);
            }
            return probabilityResult;
        }
        else return probability;
    }

    public double getNotKnownProbability() {
        if (this.notKnownProbability == 0 && !this.childNodes.isEmpty()) {
            double notKnownProbabilityResult = 0;
            for (Node node: childNodes) {
                int weightIndex = childNodes.indexOf(node);
                notKnownProbabilityResult += node.getNotKnownProbability() * this.weights.get(weightIndex);
            }
            return notKnownProbabilityResult;
        }
        else return notKnownProbability;
    }

    public double getNotLearntProbability() {
        if (this.probability == 0 && !this.childNodes.isEmpty()) {
            double notLearntProbabilityResult = 0;
            for (Node node: childNodes) {
                int weightIndex = childNodes.indexOf(node);
                notLearntProbabilityResult += (1 - node.getNotKnownProbability() - node.getProbability()) * this.weights.get(weightIndex);
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public void addWeight(Double weight) {
        this.weights.add(weight);
    }
}

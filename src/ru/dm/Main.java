package ru.dm;

public class Main {

    public static void main(String[] args) {
        /*
        Node competenceNode = new Node("Java Developer");

        Node topicNode1 = new Node("Object oriented programming", 0.23, 0.7);
        Node topicNode2 = new Node("Parallel programming", 0.08, 0.3);

        Node subTopicNode1 = new Node("Incapsulation", 0.41, 0.5);
        Node subTopicNode2 = new Node("Abstraction", 0.2, 0.5);
        topicNode1.addChildNode(subTopicNode1);
        topicNode1.addChildNode(subTopicNode2);

        competenceNode.addChildNode(topicNode1);
        competenceNode.addChildNode(topicNode2);

        System.out.println(competenceNode.getProbability());
        */

        Node node1 = new Node("Node", Type.Competence, 0, 0);
        Node node2 = new Node("Node",  Type.Topic, 0, 0.5);
        Node node3 = new Node("Node", Type.Topic,0.2, 0.5);
        Node node21 = new Node("Node", Type.Subtopic, 0, 0.3);
        Node node22 = new Node("Node", Type.Subtopic,0.1, 0.7);
        Node node211 = new Node("Node", Type.Test,0.22, 0.5);
        Node node212 = new Node("Node", Type.Test,0.54, 0.5);

        node211.setNotKnownProbability(0.05);
        node212.setNotKnownProbability(0.12);

        node21.addChildNode(node211);
        node21.addChildNode(node212);
        node2.addChildNode(node21);
        node2.addChildNode(node22);
        node1.addChildNode(node2);
        node1.addChildNode(node3);

        System.out.println(node1.getProbability());
        System.out.println(node1.getNotKnownProbability());
    }
}

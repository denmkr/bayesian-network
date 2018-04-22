package ru.dm;

import java.util.List;

public class Main {

    public static void main(String[] args) {

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

        Student student = new Student("Denis", "11401");
        student.addCourse(new Course("Java course", node1));

        List<Course> courses = student.getCourses();
        Course course = courses.get(0);

        System.out.println(course.getCompetenceNode().getProbability());
        System.out.println(course.getCompetenceNode().getNotKnownProbability());
    }
}

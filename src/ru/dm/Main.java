package ru.dm;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Node competenceNode1 = new Node("Competence1", Type.Competence, 0);
        competenceNode1.addWeight(0.5);
        competenceNode1.addWeight(0.5);
        Node topicNode1 = new Node("Topic1",  Type.Topic, 0);
        Node topicNode2 = new Node("Topic2", Type.Topic,0.6);
        topicNode1.addWeight(0.3);
        topicNode1.addWeight(0.7);
        Node subTopicNode1 = new Node("Subtopic1", Type.Subtopic, 0);
        subTopicNode1.addWeight(0.5);
        subTopicNode1.addWeight(0.5);
        Node subTopicNode2 = new Node("Subtopic2", Type.Subtopic,0.4);

        /* Test nodes */
        Node testNode1 = new Node("Test1", Type.Test,0);
        testNode1.addWeight(0.4);
        testNode1.addWeight(0.6);
        Node testNode2 = new Node("Test2", Type.Test,0);
        testNode2.addWeight(0.8);
        testNode2.addWeight(0.2);

        /* Question nodes */
        List<String> answers = new ArrayList<String>();
        answers.add("1");
        answers.add("2");

        QuestionNode questionNode1 = new QuestionNode(new Question("Question one", answers));
        questionNode1.setProbability(0.5);
        QuestionNode questionNode2 = new QuestionNode(new Question("Question two", answers));
        questionNode2.setProbability(1);
        QuestionNode questionNode3 = new QuestionNode(new Question("Question three", answers));
        questionNode3.setProbability(0.2);

        /* Adding childNodes */
        testNode1.addChildNode(questionNode1);
        testNode1.addChildNode(questionNode2);
        testNode2.addChildNode(questionNode2);
        testNode2.addChildNode(questionNode3);

        subTopicNode1.addChildNode(testNode1);
        subTopicNode1.addChildNode(testNode2);

        topicNode1.addChildNode(subTopicNode1);
        topicNode1.addChildNode(subTopicNode2);

        competenceNode1.addChildNode(topicNode1);
        competenceNode1.addChildNode(topicNode2);

        Student student = new Student("Denis", "11401");
        student.addCourse(new Course("Java course", competenceNode1));

        List<Course> courses = student.getCourses();
        Course course = courses.get(0);

        System.out.println("Competence: " + course.getCompetenceNode().getProbability() * 100 + "%");

    }
}

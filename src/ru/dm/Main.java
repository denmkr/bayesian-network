package ru.dm;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /* NETWORK BUILDING */

        Node competenceNode1 = new Node("Competence1", Type.Competence, 0);
        competenceNode1.addWeight(0.5);
        competenceNode1.addWeight(0.5);
        Node topicNode1 = new Node("Topic1",  Type.Topic, 0);
        topicNode1.addWeight(0.3);
        topicNode1.addWeight(0.7);
        Node topicNode2 = new Node("Topic2", Type.Topic,0);
        topicNode2.addWeight(1.0);
        Node subTopicNode1 = new Node("Subtopic1", Type.Subtopic, 0);
        subTopicNode1.addWeight(0.5);
        subTopicNode1.addWeight(0.5);
        Node subTopicNode2 = new Node("Subtopic2", Type.Subtopic,0);
        subTopicNode2.addWeight(1.0);
        Node subTopicNode3 = new Node("Subtopic3", Type.Subtopic,0);
        subTopicNode3.addWeight(0.3);
        subTopicNode3.addWeight(0.7);

        /* Test nodes */
        Node testNode1 = new Node("Test1", Type.Test,0);
        testNode1.addWeight(0.4);
        testNode1.addWeight(0.6);
        Node testNode2 = new Node("Test2", Type.Test,0);
        testNode2.addWeight(0.8);
        testNode2.addWeight(0.2);
        Node testNode3 = new Node("Test3", Type.Test,0);
        testNode3.addWeight(1.0);
        Node testNode4 = new Node("Test4", Type.Test,0);
        testNode4.addWeight(1.0);

        /* Question nodes */
        List<String> answers = new ArrayList<String>();
        answers.add("1");
        answers.add("2");

        QuestionNode questionNode1 = new QuestionNode(new Question("Question one", answers));
        QuestionNode questionNode2 = new QuestionNode(new Question("Question two", answers));
        QuestionNode questionNode3 = new QuestionNode(new Question("Question three", answers));
        QuestionNode questionNode4 = new QuestionNode(new Question("Question four", answers));
        QuestionNode questionNode5 = new QuestionNode(new Question("Question five", answers));

        /* Adding childNodes */
        testNode1.addChildNode(questionNode1);
        testNode1.addChildNode(questionNode2);
        testNode2.addChildNode(questionNode2);
        testNode2.addChildNode(questionNode3);
        testNode3.addChildNode(questionNode4);
        testNode4.addChildNode(questionNode5);

        subTopicNode1.addChildNode(testNode1);
        subTopicNode1.addChildNode(testNode2);
        subTopicNode2.addChildNode(testNode3);
        subTopicNode3.addChildNode(testNode3);
        subTopicNode3.addChildNode(testNode4);

        topicNode1.addChildNode(subTopicNode1);
        topicNode1.addChildNode(subTopicNode2);
        topicNode2.addChildNode(subTopicNode3);

        competenceNode1.addChildNode(topicNode1);
        competenceNode1.addChildNode(topicNode2);


        /* STUDENTS CREATING */

        Student student1 = new Student("Denis", "11401");
        questionNode1.setProbability(0.25);
        questionNode2.setProbability(0.5);
        questionNode3.setProbability(0);
        questionNode4.setProbability(0.22);
        questionNode5.setProbability(1);

        questionNode1.setNotKnownProbability(0.75);
        questionNode2.setNotKnownProbability(0.5);
        questionNode3.setNotKnownProbability(1);
        questionNode4.setNotKnownProbability(0.5);
        questionNode5.setNotKnownProbability(0);

        student1.addCourse(new Course("Java Course", competenceNode1));
        System.out.println("Competence of " + student1.getName() + ": " + student1.getCourses().get(0).getCompetenceNode().getProbability() * 100 + "%");
        System.out.println("Error of " + student1.getName() + ": " + student1.getCourses().get(0).getCompetenceNode().getNotKnownProbability() * 100 + "%");
        System.out.println("Not Learnt of " + student1.getName() + ": " + student1.getCourses().get(0).getCompetenceNode().getNotLearntProbability() * 100 + "%");
        System.out.println();

        Student student2 = new Student("Maxim", "11402");
        questionNode1.setProbability(0.2);
        questionNode2.setProbability(0);
        questionNode3.setProbability(0.4);
        questionNode4.setProbability(1);
        questionNode5.setProbability(0.75);

        questionNode1.setNotKnownProbability(0.15);
        questionNode2.setNotKnownProbability(0.5);
        questionNode3.setNotKnownProbability(0);
        questionNode4.setNotKnownProbability(0);
        questionNode5.setNotKnownProbability(0.25);

        student2.addCourse(new Course("Java Course", competenceNode1));
        System.out.println("Competence of " + student2.getName() + ": " + student2.getCourses().get(0).getCompetenceNode().getProbability() * 100 + "%");
        System.out.println("Error of " + student2.getName() + ": " + student2.getCourses().get(0).getCompetenceNode().getNotKnownProbability() * 100 + "%");
        System.out.println("Not Learnt of " + student2.getName() + ": " + student2.getCourses().get(0).getCompetenceNode().getNotLearntProbability() * 100 + "%");
    }
}

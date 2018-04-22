package ru.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Course course = createCourse();
        List<Student> students = createStudents();

        for (Student student: students) {
            student.addCourse(course);
        }

        updateStudentsGrades(students);
        updateStudentsErrors(students);
        showStatisticByStudents(students);
    }

    public static Course createCourse() {

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

        return new Course("Java", competenceNode1);
    }

    public static List<Student> createStudents() {

        Student student1 = new Student("Denis", "11401");
        Student student2 = new Student("Maxim", "11402");

        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);

        return students;
    }

    public static void updateStudentsGrades(List<Student> students) {
        Vector<Double> gradesVector1 = new Vector<Double>();
        gradesVector1.add(0.75);
        gradesVector1.add(0.5);
        gradesVector1.add(0.0);
        gradesVector1.add(0.22);
        gradesVector1.add(0.88);
        List<Vector<Double>> gradesList1 = new ArrayList<Vector<Double>>();
        gradesList1.add(gradesVector1);
        students.get(0).setGrades(gradesList1);

        Vector<Double> gradesVector2 = new Vector<Double>();
        gradesVector2.add(0.2);
        gradesVector2.add(0.0);
        gradesVector2.add(0.4);
        gradesVector2.add(1.0);
        gradesVector2.add(0.75);
        List<Vector<Double>> gradesList2 = new ArrayList<Vector<Double>>();
        gradesList2.add(gradesVector2);
        students.get(1).setGrades(gradesList2);
    }

    public static void updateStudentsErrors(List<Student> students) {
        Vector<Double> errorsVector1 = new Vector<Double>();
        errorsVector1.add(0.25);
        errorsVector1.add(0.1);
        errorsVector1.add(0.0);
        errorsVector1.add(0.11);
        errorsVector1.add(0.0);
        List<Vector<Double>> errorsList1 = new ArrayList<Vector<Double>>();
        errorsList1.add(errorsVector1);
        students.get(0).setErrors(errorsList1);

        Vector<Double> errorsVector2 = new Vector<Double>();
        errorsVector2.add(0.8);
        errorsVector2.add(1.0);
        errorsVector2.add(0.1);
        errorsVector2.add(0.0);
        errorsVector2.add(0.11);
        List<Vector<Double>> errorsList2 = new ArrayList<Vector<Double>>();
        errorsList2.add(errorsVector2);
        students.get(1).setErrors(errorsList2);
    }

    public static void showStatisticByStudents(List<Student> students) {
        for (Student student: students) {
            student.calculateNetwork();
            System.out.println(student.getName());
            for (Course course: student.getCourses()) {
                System.out.println(course.getName());
                System.out.println("Competence: " + course.getCompetenceLevelResult() * 100 + "%");
                System.out.println("Error: " + course.getCompetenceErrorResult() * 100 + "%");
                System.out.println("Not Learnt: " + course.getCompetenceNotLearntResult() * 100 + "%");
                System.out.println();
            }
        }
    }
}

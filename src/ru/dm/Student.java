package ru.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by denis on 21/04/2018.
 */
public class Student {
    private String name;
    private String group;
    private List<String> activityDates;
    private List<Course> courses = new ArrayList<Course>();
    private List<Vector<Double>> grades = new ArrayList<Vector<Double>>();
    private List<Vector<Double>> errors = new ArrayList<Vector<Double>>();

    public Student(String name, String group, List<Course> courses) {
        this.name = name;
        this.group = group;
        this.courses = courses;
    }

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public List<String> getActivityDates() {
        return activityDates;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setActivityDates(List<String> datesOfActivity) {
        this.activityDates = datesOfActivity;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Vector<Double>> getGrades() {
        return grades;
    }

    public void setGrades(List<Vector<Double>> gradesList) {
        this.grades = gradesList;
    }

    public void addGrades(Vector<Double> grades) {
        this.grades.add(grades);
    }

    public List<Vector<Double>> getErrors() {
        return errors;
    }

    public void setErrors(List<Vector<Double>> errorsList) {
        this.errors = errorsList;
    }

    public void addErrors(Vector<Double> errors) {
        this.errors.add(errors);
    }

    public void calculateNetwork() {
        int gradeIndex = 0;
        int courseIndex = 0;
        List<QuestionNode> usedQuestionNodes = new ArrayList<QuestionNode>();

        for (Course course: this.courses) {
            Node competenceNode = course.getCompetenceNode();
            for (Node topicNode: competenceNode.getChildNodes()) {
                for (Node subTopicNode: topicNode.getChildNodes()) {
                    for (Node testNode: subTopicNode.getChildNodes()) {
                        for (Node questionNode: testNode.getChildNodes()) {
                            if (!((QuestionNode) questionNode).isChanged()) {
                                questionNode.setProbability(this.grades.get(courseIndex).get(gradeIndex));
                                questionNode.setNotKnownProbability(this.errors.get(courseIndex).get(gradeIndex));
                                ((QuestionNode) questionNode).setChanged(true);
                                usedQuestionNodes.add((QuestionNode) questionNode);
                                gradeIndex++;
                            }
                        }
                    }
                }
            }
            courseIndex++;
        }

        for (QuestionNode questionNode: usedQuestionNodes) {
            questionNode.setChanged(false);
        }
    }

    public void addActivityDate(String date) {
        this.activityDates.add(date);
    }
}

package movies.spring.data.neo4j.domain;

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
        List<Node> usedQuestionNodes = new ArrayList<Node>();

        for (Course course: this.courses) {
            Node competenceNode = course.getCompetenceNode();
            for (Relation topicRelation: competenceNode.getRelationships()) {
                Node topicNode = topicRelation.getNextNode();
                for (Relation subTopicRelation: topicNode.getRelationships()) {
                    Node subTopicNode = subTopicRelation.getNextNode();
                    for (Relation testRelation: subTopicNode.getRelationships()) {
                        Node testNode = testRelation.getNextNode();
                        for (Relation questionRelation: testNode.getRelationships()) {
                            Node questionNode = questionRelation.getNextNode();
                            if (!questionNode.isChanged()) {
                                questionNode.setProbability(this.grades.get(courseIndex).get(gradeIndex));
                                questionNode.setNotKnownProbability(this.errors.get(courseIndex).get(gradeIndex));
                                questionNode.setChanged(true);
                                usedQuestionNodes.add(questionNode);
                                gradeIndex++;
                            }
                        }
                    }
                }
            }
            courseIndex++;
        }

        for (Node questionNode: usedQuestionNodes) {
            questionNode.setChanged(false);
        }
    }

    public void getErrorSubTopicNodes() {
        System.out.println("Error subtopics:");
        for (Course course: this.courses) {
            for (Relation topicRelation: course.getCompetenceNode().getRelationships()) {
                Node topicNode = topicRelation.getNextNode();
                for (Relation subTopicRelation: topicNode.getRelationships()) {
                    Node subTopicNode = subTopicRelation.getNextNode();
                    if (subTopicNode.getNotKnownProbability() > 0.10) {
                        System.out.println(subTopicNode.getName());
                    }
                }
            }
        }
    }

    public void addActivityDate(String date) {
        this.activityDates.add(date);
    }
}

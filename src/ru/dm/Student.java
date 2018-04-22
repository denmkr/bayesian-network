package ru.dm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 21/04/2018.
 */
public class Student {
    private String name;
    private String group;
    private List<String> activityDates;
    private List<Course> courses = new ArrayList<Course>();

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

    public void addActivityDate(String date) {
        this.activityDates.add(date);
    }
}

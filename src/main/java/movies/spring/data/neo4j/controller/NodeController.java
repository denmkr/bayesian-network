package movies.spring.data.neo4j.controller;

import movies.spring.data.neo4j.domain.*;
import movies.spring.data.neo4j.services.NodeService;
import movies.spring.data.neo4j.views.NodeView;
import movies.spring.data.neo4j.views.TopicView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/")
public class NodeController {

    private final NodeService nodeService;
	
	public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
	}

    @GetMapping("/stat")
    public void showStatistics() {
        Iterator<Node> result = nodeService.findAll().iterator();
        Node competenceNode = new Node();
        while (result.hasNext()) {
            Node node = result.next();
            if (node.getType().equals(Type.Competence)) {
                competenceNode = node;
                continue;
            }
        }

        Course course = new Course("Java", competenceNode);
        List<Student> students = createStudents();

        for (Student student: students) {
            student.addCourse(course);
        }

        updateStudentsGrades(students);
        updateStudentsErrors(students);
        showStatisticByStudents(students);
    }

    @GetMapping("/nodes")
    public Iterable<Node> findAll() {
        Iterator<Node> result = nodeService.findAll().iterator();
        Node competenceNode = new Node();
        while (result.hasNext()) {
            Node node = result.next();
            if (node.getType().equals(Type.Competence)) {
                competenceNode = node;
                continue;
            }
        }

        System.out.println(competenceNode.getRelationships().get(0).getNextNode().getRelationships().get(0).getNextNode().getRelationships().get(0).getNextNode().getRelationships().get(0).getNextNode().getName());

        return nodeService.findAll();
    }

    @GetMapping("/node")
    public Node findByName() {
        Iterator<Node> result = nodeService.findAll().iterator();
        Node competenceNode = new Node();
        while (result.hasNext()) {
            Node node = result.next();
            if (node.getType().equals(Type.Competence)) {
                competenceNode = node;
                continue;
            }
        }

        System.out.println(competenceNode.getName());
        System.out.println(competenceNode.getRelationships().get(0).getNextNode().getRelationships());
        return competenceNode;
    }

    /* */

    public Node getCompetenceByStudent(Student student) {
        student.calculateNetwork();
        Course course = student.getCourses().get(0);

        return course.getCompetenceNode();
    }

    public void showStatisticByStudents(List<Student> students) {
        for (Student student: students) {
            student.calculateNetwork();
            System.out.println(student.getName());
            for (Course course: student.getCourses()) {
                System.out.println(course.getName());
                System.out.println("Competence: " + String.format("%.2f", course.getCompetenceLevelResult() * 100) + "%");
                System.out.println("Error: " + String.format("%.2f", course.getCompetenceErrorResult() * 100) + "%");
                student.getErrorSubTopicNodes();

                System.out.println("Not Learnt: " + String.format("%.2f", course.getCompetenceNotLearntResult() * 100) + "%");
                System.out.println();
            }
        }
    }

    public List<Student> createStudents() {

        Student student1 = new Student("Denis", "11401");
        Student student2 = new Student("Maxim", "11402");

        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);

        return students;
    }

    public void updateStudentsGrades(List<Student> students) {
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

    public void updateStudentsErrors(List<Student> students) {
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
}

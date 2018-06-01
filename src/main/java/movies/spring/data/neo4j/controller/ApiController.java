package movies.spring.data.neo4j.controller;

import movies.spring.data.neo4j.domain.*;
import movies.spring.data.neo4j.services.NodeService;
import movies.spring.data.neo4j.views.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final NodeService nodeService;

    public ApiController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/skills")
    public BurstNodeView getTreeWithProbabilities() {
        Node competenceNode = getCompetenceNodeWithChildren();

        Course course = new Course("Java", competenceNode);
        Student student = createOneStudent();
        student.addCourse(course);

        updateStudentGrades(student);
        updateStudentErrors(student);

        competenceNode = getCompetenceByStudent(student);

        BurstNodeView competenceNodeView = new BurstNodeView(competenceNode.getName());
        competenceNodeView.setValue(competenceNode.getProbability());

        for (Relation competenceRelation: competenceNode.getRelationships()) {
            float competenceWeight = competenceRelation.getWeight();
            Node topicNode = competenceRelation.getNextNode();
            BurstNodeView topicNodeView = new BurstNodeView(topicNode.getName());
            topicNodeView.setValue(topicNode.getProbability());
            competenceNodeView.addChild(topicNodeView);
            for (Relation topicRelation: topicNode.getRelationships()) {
                float topicWeight = competenceWeight * topicRelation.getWeight();
                Node subTopicNode = topicRelation.getNextNode();
                BurstNodeView subTopicNodeView = new BurstNodeView(subTopicNode.getName());
                subTopicNodeView.setValue(subTopicNode.getProbability());
                topicNodeView.addChild(subTopicNodeView);
                for (Relation subTopicRelation: subTopicNode.getRelationships()) {
                    float subtopicWeight = topicWeight * subTopicRelation.getWeight();
                    Node testNode = subTopicRelation.getNextNode();
                    BurstNodeView testNodeView = new BurstNodeView(testNode.getName());
                    testNodeView.setValue(testNode.getProbability());
                    subTopicNodeView.addChild(testNodeView);
                    for (Relation testRelation: testNode.getRelationships()) {
                        float testWeight = subtopicWeight * testRelation.getWeight();
                        Node questionNode = testRelation.getNextNode();
                        BurstNodeView questionNodeView = new BurstNodeView(questionNode.getName());
                        questionNodeView.setValue(questionNode.getProbability());
                        questionNodeView.setSize(testWeight);
                        testNodeView.addChild(questionNodeView);
                    }
                }
            }
        }

        return competenceNodeView;
    }

    @GetMapping("/diagram")
    public List<StudentView> getStudentsWithProbabilities() {
        Node competenceNode = getCompetenceNodeWithChildren();

        Course course = new Course("Java", competenceNode);
        List<Student> students = createStudents();

        List<StudentView> studentViews = new ArrayList<>();

        for (Student student: students) {
            student.addCourse(course);
            updateStudentGrades(student);
            updateStudentErrors(student);

            competenceNode = getCompetenceByStudent(student);
            studentViews.add(new StudentView(student.getName(), competenceNode.getProbability(), competenceNode.getNotKnownProbability()));
        }

        return studentViews;
    }

    @GetMapping("/digits")
    public List<Double> getProbabilities() {
        Node competenceNode = getCompetenceNodeWithChildren();

        Course course = new Course("Java", competenceNode);
        Student student = createOneStudent();
        student.addCourse(course);

        updateStudentGrades(student);
        updateStudentErrors(student);

        competenceNode = getCompetenceByStudent(student);

        List<Double> probabilities = new ArrayList<Double>();
        probabilities.add(competenceNode.getProbability());
        probabilities.add(competenceNode.getNotKnownProbability());

        return probabilities;
    }


    @GetMapping("/radar")
    public List<CompetenceView> getRadar() {
        Node competenceNode = getCompetenceNodeWithChildren();

        Course course = new Course("Java", competenceNode);
        Student student = createOneStudent();
        student.addCourse(course);

        updateStudentGrades(student);
        updateStudentErrors(student);

        competenceNode = getCompetenceByStudent(student);

        List<CompetenceView> competenceViews = new ArrayList<CompetenceView>();
        competenceViews.add(new CompetenceView(competenceNode.getName(), competenceNode.getProbability()));
        competenceViews.add(new CompetenceView("Competence 2", Math.random()));
        competenceViews.add(new CompetenceView("Competence 3", Math.random()));
        competenceViews.add(new CompetenceView("Competence 4", Math.random()));
        competenceViews.add(new CompetenceView("Competence 5", Math.random()));

        return competenceViews;
    }

    @GetMapping("/sunburst")
    public List<TopicView> getTopics() {
        Node competenceNode = getCompetenceNodeWithChildren();

        Course course = new Course("Java", competenceNode);
        Student student = createOneStudent();
        student.addCourse(course);

        updateStudentGrades(student);
        updateStudentErrors(student);

        competenceNode = getCompetenceByStudent(student);

        List<TopicView> topicViews = new ArrayList<TopicView>();
        for (Relation relation: competenceNode.getRelationships()) {
            Node node = relation.getNextNode();
            TopicView topicView = new TopicView(node.getId(), node.getName(), node.getProbability() * 100);
            topicView.setWeight(relation.getWeight());
            topicViews.add(topicView);
        }

        return topicViews;
    }

    @GetMapping("/tree")
    public NodeView getTreeOfNodes() {
        Node competenceNode = getCompetenceNodeWithChildren();
        NodeView competenceNodeView = new NodeView(competenceNode.getName());

        for (Relation competenceRelation: competenceNode.getRelationships()) {
            Node topicNode = competenceRelation.getNextNode();
            NodeView topicNodeView = new NodeView(topicNode.getName());
            competenceNodeView.addChild(topicNodeView);
            for (Relation topicRelation: topicNode.getRelationships()) {
                Node subTopicNode = topicRelation.getNextNode();
                NodeView subTopicNodeView = new NodeView(subTopicNode.getName());
                topicNodeView.addChild(subTopicNodeView);
                for (Relation subTopicRelation: subTopicNode.getRelationships()) {
                    Node testNode = subTopicRelation.getNextNode();
                    NodeView testNodeView = new NodeView(testNode.getName());
                    subTopicNodeView.addChild(testNodeView);
                    for (Relation testRelation: testNode.getRelationships()) {
                        Node questionNode = testRelation.getNextNode();
                        NodeView questionNodeView = new NodeView(questionNode.getName());
                        testNodeView.addChild(questionNodeView);
                    }
                }
            }
        }

        return competenceNodeView;
    }

    @GetMapping("/graph/nodes")
    public List<NodeView> getGraphNodes() {
        List<NodeView> nodeViews = new ArrayList<NodeView>();
        for (Node node: nodeService.getAll()) {
            nodeViews.add(new NodeView(node.getId(), node.getName()));
        }

        return nodeViews;
    }

    @GetMapping("/graph/links")
    public List<LinkView> getGraphLinks() {
        List<LinkView> linkViews = new ArrayList<LinkView>();
        Iterator<Node> result = nodeService.findAll().iterator();
        while (result.hasNext()) {
            Node node = result.next();
            if (!node.getType().equals(Type.Question)) {
                for (Relation relation: node.getRelationships()) {
                    linkViews.add(new LinkView(node.getId(), relation.getNextNode().getId(), relation.getWeight().toString()));
                }
            }
        }

        return linkViews;
    }

    /* Auxiliary functions */

    public Node getCompetenceNodeWithChildren() {
        Iterator<Node> result = nodeService.findAll().iterator();
        Node competenceNode = new Node();
        while (result.hasNext()) {
            Node node = result.next();
            if (node.getType().equals(Type.Competence)) {
                competenceNode = node;
                continue;
            }
        }

        return competenceNode;
    }

    public Node getCompetenceByStudent(Student student) {
        student.calculateNetwork();
        Course course = student.getCourses().get(0);

        return course.getCompetenceNode();
    }

    public Student createOneStudent() {
        Student student = new Student("Denis Makarov", "11401");
        return student;
    }

    public List<Student> createStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Denis Makarov", "11401"));
        students.add(new Student("Eliz Schlatter", "11401"));
        students.add(new Student("Madison Tesar", "11401"));
        students.add(new Student("Salena Avery", "11401"));
        students.add(new Student("Joanne Gisler", "11401"));
        students.add(new Student("Nicky Fu", "11401"));
        students.add(new Student("Bridgette Soltys", "11401"));
        students.add(new Student("Ava Bjornson", "11401"));
        students.add(new Student("Wilton Desir", "11401"));
        students.add(new Student("Antwan Cumming", "11401"));
        students.add(new Student("Mara Brian", "11401"));
        students.add(new Student("Digna Lesser", "11401"));
        students.add(new Student("Nicol Gowen", "11401"));
        students.add(new Student("Ivana Caruso", "11401"));
        students.add(new Student("Doris Mcphearson", "11401"));
        students.add(new Student("Gricelda Rothenberger", "11401"));
        students.add(new Student("Albert Callender", "11401"));
        students.add(new Student("So Haywood", "11401"));
        students.add(new Student("Cristie Duffer", "11401"));
        students.add(new Student("Karl Poss", "11401"));

        return students;
    }

    public void updateStudentGrades(Student student) {
        Vector<Double> gradesVector = new Vector<Double>();
        gradesVector.add(Math.random() * 0.7);
        gradesVector.add(Math.random() * 0.7);
        gradesVector.add(Math.random() * 0.7);
        gradesVector.add(Math.random() * 0.7);
        gradesVector.add(Math.random() * 0.7);
        List<Vector<Double>> gradesList = new ArrayList<Vector<Double>>();
        gradesList.add(gradesVector);
        student.setGrades(gradesList);
    }

    public void updateStudentErrors(Student student) {
        Vector<Double> errorsVector = new Vector<Double>();
        errorsVector.add(Math.random() * 0.3);
        errorsVector.add(Math.random() * 0.3);
        errorsVector.add(Math.random() * 0.3);
        errorsVector.add(Math.random() * 0.3);
        errorsVector.add(Math.random() * 0.3);
        List<Vector<Double>> errorsList = new ArrayList<Vector<Double>>();
        errorsList.add(errorsVector);
        student.setErrors(errorsList);
    }

}

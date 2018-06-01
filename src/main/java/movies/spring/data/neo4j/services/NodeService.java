package movies.spring.data.neo4j.services;

import movies.spring.data.neo4j.domain.Node;
import movies.spring.data.neo4j.domain.QuestionNode;
import movies.spring.data.neo4j.repositories.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by denis on 23/05/2018.
 */

@Service
public class NodeService {

    private final static Logger LOG = LoggerFactory.getLogger(NodeService.class);

    private final NodeRepository nodeRepository;
    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Node> getGraph() {
        Collection<Node> result = nodeRepository.graph(30);
        return result;
    }

    @Transactional(readOnly = true)
    public Node findByName(String name) {
        Node result = nodeRepository.findByName(name);
        return result;
    }

    @Transactional(readOnly = true)
    public QuestionNode findQuestionByName(String name) {
        QuestionNode result = nodeRepository.findQuestionByName(name);
        return result;
    }

    public Iterable<Node> findAll() {
        return nodeRepository.findAll();
    }

    public List<Node> getAll() {
        return nodeRepository.getAll();
    }

}

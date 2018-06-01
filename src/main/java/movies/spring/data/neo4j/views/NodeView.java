package movies.spring.data.neo4j.views;

import movies.spring.data.neo4j.domain.*;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 31/05/2018.
 */

public class NodeView {

    private Long id;
    private String name;
    private List<NodeView> children = new ArrayList<>();

    public NodeView() {
    }

    public NodeView(String name) {
        this.name = name;
    }

    public NodeView(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NodeView> getChildren() {
        return children;
    }

    public void setChildren(List<NodeView> children) {
        this.children = children;
    }

    public void addChild(NodeView child) {
        this.children.add(child);
    }

}
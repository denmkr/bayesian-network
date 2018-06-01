package movies.spring.data.neo4j.views;

import movies.spring.data.neo4j.domain.*;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 31/05/2018.
 */

public class BurstNodeView {

    private double value;
    private double size;
    private String name;
    private List<BurstNodeView> children = new ArrayList<>();

    public BurstNodeView() {
    }

    public BurstNodeView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BurstNodeView> getChildren() {
        return children;
    }

    public void setChildren(List<BurstNodeView> children) {
        this.children = children;
    }

    public void addChild(BurstNodeView child) {
        this.children.add(child);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
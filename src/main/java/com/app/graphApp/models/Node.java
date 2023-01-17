package com.app.graphApp.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.*;

@org.springframework.data.neo4j.core.schema.Node
public class Node {
    @Id @Getter @Setter(AccessLevel.PROTECTED)
    private Long id;
    @Property @Getter @Setter
    private String label;
    @Property @Getter @Setter
    private ArrayList<NodeDataEntry> data;

    public Node() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Node(String label, ArrayList<NodeDataEntry> data) {
        this.label = label;
        this.data = data;
    }

    @Relationship(type = "RELATED")
    public Set<Node> related;

    public void newRelation(Node node) {
        if (related == null) {
            related = new HashSet<>();
        }
        related.add(node);
    }

    public String toString() {
        return this.id +
                this.label +
                "related to - " +
                Optional.ofNullable(this.related).orElse(
                                Collections.emptySet()).stream()
                        .map(Node::getId).toList();
    }
}
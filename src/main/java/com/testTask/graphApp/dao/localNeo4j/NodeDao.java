package com.testTask.graphApp.dao.localNeo4j;

import com.testTask.graphApp.dao.Dao;
import com.testTask.graphApp.models.Node;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

import java.util.List;
import java.util.Optional;

public class NodeDao implements Dao<Node> {
    private final Driver driver;

    public NodeDao(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Optional<Node> get(String id) {
        try (Session session = driver.session()) {
            List<String> bla = session.run("MATCH (m:Movie) RETURN m ORDER BY m.name ASC").list(r -> r.get("m").asNode().get("title").asString());
        }
        return Optional.empty();
    }

    @Override
    public List<Node> getAll() {
        return null;
    }

    @Override
    public Node save(Node node) {
        return null;
    }

    @Override
    public void update(Node node, String[] params) {

    }

    @Override
    public void delete(Node node) {

    }
}

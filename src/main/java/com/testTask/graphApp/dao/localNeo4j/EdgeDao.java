package com.testTask.graphApp.dao.localNeo4j;

import com.testTask.graphApp.dao.Dao;
import com.testTask.graphApp.models.Edge;

import java.util.List;
import java.util.Optional;

public class EdgeDao implements Dao<Edge> {
    @Override
    public Optional<Edge> get(String id) {
        return Optional.empty();
    }

    @Override
    public List<Edge> getAll() {
        return null;
    }

    @Override
    public Edge save(Edge edge) {
        return null;
    }

    @Override
    public void update(Edge edge, String[] params) {

    }

    @Override
    public void delete(Edge edge) {

    }
}

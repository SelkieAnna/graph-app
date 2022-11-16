package com.testTask.graphApp.dao.local;

import com.testTask.graphApp.dao.Dao;
import com.testTask.graphApp.models.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EdgeDao implements Dao<Edge> {

    private static final ArrayList<Edge> edges = new ArrayList<>(Arrays.asList(
            new Edge("0", "0", "2"),
            new Edge("1", "0", "1")
    ));

    @Override
    public Optional<Edge> get(String id) {
        return edges.stream().filter(edge -> edge.getId().equals(id)).findFirst();
    }

    @Override
    public List<Edge> getAll() {
        return edges.stream().toList();
    }

    /**
     * Add a new edgeto the storage
     * @param edge edge that needs to be added to the storage
     *             can contain source and target nodes
     *             id will be overwritten
     * @return added edge with updated id
     */
    @Override
    public Edge save(Edge edge) {
        Edge newEdge = new Edge(newId(), edge.getSource(), edge.getTarget());
        edges.add(newEdge);
        return newEdge;
    }

    /**
     * Update source and target of an existing edge
     * @param edge updated edge with id and old data
     * @param params params[0] - source node id
     *               params[1] - target node id
     */
    @Override
    public void update(Edge edge, String[] params) {
        edge.setSource(params[0]);
        edge.setTarget(params[1]);
    }

    @Override
    public void delete(Edge edge) {
        edges.remove(edge);
    }

    /**
     * Get id for a new edge
     * @return new id
     */
    private String newId() {
        return Integer.toString(Integer.parseInt(edges.get(edges.size() - 1).getId()) + 1);
    }
}

package com.testTask.graphApp.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.testTask.graphApp.models.Edge;
import com.testTask.graphApp.dao.local.EdgeDao;

import java.util.Optional;

@Controller
public class EdgeController {

    static EdgeDao edgeDao = new EdgeDao();

    @MutationMapping
    public Edge linkNodes(@Argument String sourceId, @Argument String targetId) {
        return edgeDao.save(new Edge("", sourceId, targetId));
    }

    @MutationMapping
    public Edge dropEdge(@Argument String id) {
        Optional<Edge> edge = edgeDao.get(id);
        edge.ifPresent(e -> edgeDao.delete(e));
        return edge.get();
    }
}

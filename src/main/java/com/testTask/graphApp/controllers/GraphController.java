package com.testTask.graphApp.controllers;

import com.testTask.graphApp.dao.local.EdgeDao;
import com.testTask.graphApp.dao.local.NodeDao;
import com.testTask.graphApp.models.Graph;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphController {

    NodeDao nodeDao = NodeController.nodeDao;
    EdgeDao edgeDao = EdgeController.edgeDao;

    @QueryMapping
    public Graph graph() {
        return new Graph(nodeDao.getAll(), edgeDao.getAll());
    }
}

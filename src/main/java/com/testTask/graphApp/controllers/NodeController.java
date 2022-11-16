package com.testTask.graphApp.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.testTask.graphApp.models.Node;
import com.testTask.graphApp.models.NodeDataEntry;
import com.testTask.graphApp.dao.local.NodeDao;
import com.testTask.graphApp.dao.local.EdgeDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NodeController {

    static NodeDao nodeDao = new NodeDao();
    EdgeDao edgeDao = EdgeController.edgeDao;

    @MutationMapping
    public Node addNode(@Argument String label) {
        Node node = new Node("", label, new ArrayList<>());
        return nodeDao.save(node);
    }

    @MutationMapping
    public Node setNodeLabel(@Argument String nodeId, @Argument String nodeLabel) {
        Optional<Node> node = nodeDao.get(nodeId);
        node.ifPresent(n -> nodeDao.update(n, new String[]{nodeLabel}));
        node = nodeDao.get(nodeId);
        return node.get();
    }

    @MutationMapping
    public Node setNodeData(@Argument String nodeId, @Argument List<NodeDataEntry> data) {
        Optional<Node> node = nodeDao.get(nodeId);
        // List<NodeDataEntry> to String[]
        List<String> updatedData = new ArrayList<>();
        updatedData.add("");
        data.forEach(nodeData -> {
                    updatedData.add(nodeData.getKey());
                    updatedData.add(nodeData.getValue());
                });
        node.ifPresent(n -> nodeDao.update(n, updatedData.toArray(new String[0])));
        node = nodeDao.get(nodeId);
        return node.get();
    }

    @MutationMapping
    public Node dropNode(@Argument String id) {
        Optional<Node> node = nodeDao.get(id);
        // delete related edges
        node.ifPresent(n -> {
                    edgeDao.getAll().stream()
                            .filter(edge -> edge.getSource().equals(n.getId()))
                            .forEach(edgeDao::delete);
                    edgeDao.getAll().stream()
                            .filter(edge -> edge.getTarget().equals(n.getId()))
                            .forEach(edgeDao::delete);
                });
        node.ifPresent(n -> nodeDao.delete(n));
        return node.get();
    }

}

package com.app.graphApp.controllers;

import com.app.graphApp.models.Node;
import com.app.graphApp.repo.NodeRepository;
import com.app.graphApp.models.NodeDataEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    public NodeController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @MutationMapping
    public Node addNode(@Argument String label) {
        Node node = new Node(label, new ArrayList<>());
        nodeRepository.save(node);
        return node;
    }

    @MutationMapping
    public Node setNodeLabel(@Argument Long nodeId, @Argument String nodeLabel) {
        Optional<Node> node = nodeRepository.findById(nodeId).blockOptional();
        node.ifPresent(n -> {
                    n.setData(new ArrayList<>());
                    nodeRepository.save(n);
                });
        node = nodeRepository.findById(nodeId).blockOptional();
        return node.get();
    }

    @MutationMapping
    public Node setNodeData(@Argument Long nodeId, @Argument ArrayList<NodeDataEntry> data) {
        Optional<Node> node = nodeRepository.findById(nodeId).blockOptional();
        node.ifPresent(n -> {
            n.setData(data);
            nodeRepository.save(n);
        });
        node = nodeRepository.findById(nodeId).blockOptional();
        return node.get();
    }

    @MutationMapping
    public Node dropNode(@Argument Long id) {
        Optional<Node> node = nodeRepository.findById(id).blockOptional();
        // delete related edges
//        node.ifPresent(n -> {
//                    edgeDao.getAll().stream()
//                            .filter(edge -> edge.getSource().equals(n.getId()))
//                            .forEach(edgeDao::delete);
//                    edgeDao.getAll().stream()
//                            .filter(edge -> edge.getTarget().equals(n.getId()))
//                            .forEach(edgeDao::delete);
//                });
        node.ifPresent(n -> nodeRepository.delete(n));
        return node.get();
    }

//    @MutationMapping
//    public Edge linkNodes(@Argument String sourceId, @Argument String targetId) {
//        return edgeDao.save(new Edge("", sourceId, targetId));
//    }

//    @MutationMapping
//    public Edge dropEdge(@Argument String id) {
//        Optional<Edge> edge = edgeDao.get(id);
//        edge.ifPresent(e -> edgeDao.delete(e));
//        return edge.get();
//    }

}

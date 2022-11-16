package com.testTask.graphApp.dao.local;

import com.testTask.graphApp.dao.Dao;
import com.testTask.graphApp.models.Node;
import com.testTask.graphApp.models.NodeDataEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class NodeDao implements Dao<Node> {

    private static final ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(
            new Node("0", "node zero", new ArrayList<>()),
            new Node("1", "node one", new ArrayList<>()),
            new Node("2", "node two", new ArrayList<>())
    ));

    @Override
    public Optional<Node> get(String id) {
        return nodes.stream().filter(node -> node.getId().equals(id)).findFirst();
    }

    @Override
    public List<Node> getAll() {
        return nodes.stream().toList();
    }

    /**
     * Add a new node to the storage
     * @param node node that needs to be added to the storage
     *             can contain label and data
     *             id will be overwritten and returned with node
     * @return added node with updated id
     */
    @Override
    public Node save(Node node) {
        Node newNode = new Node(newId(), node.getLabel(), node.getData());
        nodes.add(newNode);
        return newNode;
    }

    /**
     * Update label and data of an existing node
     * @param node updated node with id and old data
     * @param params params[0] - label
     *               params[1] - key
     *               params[2] - value
     *               ...
     *               params[i] - key
     *               params[i+1] - value
     *               ...
     */
    @Override
    public void update(Node node, String[] params) {
        node.setLabel(params[0]);
        ArrayList<NodeDataEntry> data = new ArrayList<>();
        IntStream.range(2, params.length)
                .mapToObj(i -> new NodeDataEntry(params[i-1], params[i]))
                .forEach(data::add);
        node.setData(data);
    }

    @Override
    public void delete(Node node) {
        nodes.remove(node);
    }

    /**
     * Get id for a new node
     * @return new id
     */
    private String newId() {
         return Integer.toString(Integer.parseInt(nodes.get(nodes.size() - 1).getId()) + 1);
    }
}

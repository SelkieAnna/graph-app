package com.testTask.graphApp.models;

import java.util.ArrayList;

public class Node {
    private String id;
    private String label;
    private ArrayList<NodeDataEntry> data;

    public Node(String id, String label, ArrayList<NodeDataEntry> data) {
        this.id = id;
        this.label = label;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<NodeDataEntry> getData() {
        return data;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setData(ArrayList<NodeDataEntry> data) {
        this.data = data;
    }
}
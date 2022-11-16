package com.testTask.graphApp.models;

public class NodeDataEntry {
    private String key;
    private String value;

    public NodeDataEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static NodeDataEntry NodeDataEntry(String key, String value) {
        return new NodeDataEntry(key, value);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
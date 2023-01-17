package com.app.graphApp.models;

import lombok.Getter;
import lombok.Setter;

public class NodeDataEntry {
    @Getter @Setter
    private String key;
    @Getter @Setter
    private String value;

    public NodeDataEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
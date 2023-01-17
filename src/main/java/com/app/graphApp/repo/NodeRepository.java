package com.app.graphApp.repo;

import com.app.graphApp.models.Node;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface NodeRepository extends ReactiveNeo4jRepository<Node, Long> {

    Node findByLabel(String label);
}

package com.karzkowiak.hierarchy.repository;

import com.karzkowiak.hierarchy.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
    Node save(Node entity);

    List<Node> findAll();

    void deleteById(String id);

    List<Node> findAllByParentId(final @NotNull @Min(1) @Max(50) String parentId);

}

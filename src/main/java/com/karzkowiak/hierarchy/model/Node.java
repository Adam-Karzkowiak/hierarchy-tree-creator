package com.karzkowiak.hierarchy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class Node {
    @Id
    @NotNull
    @Min(1)
    @Max(50)
    public String id;

    @NotNull
    @Min(1)
    @Max(100)
    public String name;

    @NotNull
    @Min(1)
    @Max(50)
    public String parentId;

    private Node(final String id, final String name, final String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public static Node create(String id, String name, String parentId) {
        return new Node(id, name, parentId);
    }
}

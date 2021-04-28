package com.karzkowiak.hierarchy.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class HierarchyTree {
    public List<HierarchySubtree> orgs = new ArrayList<>();
}

package com.karzkowiak.hierarchy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class HierarchySubtree {
    @Id
    String id;
    String name;
    @OneToMany
    public List<HierarchySubtree> orgs;


    private HierarchySubtree(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static HierarchySubtree create(String id, String name) {
        return new HierarchySubtree(id, name);
    }

}

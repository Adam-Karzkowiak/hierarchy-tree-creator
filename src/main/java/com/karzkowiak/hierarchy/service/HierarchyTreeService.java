package com.karzkowiak.hierarchy.service;

import com.karzkowiak.hierarchy.model.Node;
import com.karzkowiak.hierarchy.model.HierarchySubtree;
import com.karzkowiak.hierarchy.model.HierarchyTree;
import com.karzkowiak.hierarchy.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HierarchyTreeService {

    private final NodeRepository repository;

    HierarchyTreeService(final NodeRepository repository) {
        this.repository = repository;
    }

    public List<HierarchySubtree> createHierarchyInOrgTree(HierarchySubtree root) {
        List<Node> subs = getSubsNodesById(root.getId());
        ArrayList<HierarchySubtree> orgSubstreeList = orgToOrgSubtree(subs);
        root.orgs = orgSubstreeList;
        if (orgSubstreeList.isEmpty())
            return root.orgs;
        for (HierarchySubtree node : root.orgs) {
            createHierarchyInOrgTree(node);
        }
        return orgSubstreeList;
    }

    public HierarchyTree createOrgTree() {
        HierarchyTree hierarchyTree = new HierarchyTree();
        List<Node> allRoots = repository.findAllByParentId("root");
        for (Node node : allRoots) {
            var orgSubtree = HierarchySubtree.create(node.getId(), node.getName());
            orgSubtree.orgs = createHierarchyInOrgTree(orgSubtree);
            sort(orgSubtree);
            hierarchyTree.orgs.add(orgSubtree);

        }
        return hierarchyTree;
    }

    void sort(HierarchySubtree sub) {
        if (sub.orgs != null) {
            sub.orgs.sort(Comparator.comparing(HierarchySubtree::getName, String.CASE_INSENSITIVE_ORDER));
            for (HierarchySubtree node : sub.orgs) {
                sort(node);
            }
        }
    }


    private List<Node> getSubsNodesById(String rootId) {
        List<Node> subsNodes = new ArrayList<>();
        List<Node> orgsList = repository.findAll();
        for (Node node : orgsList) {
            if (node.getParentId().equals(rootId))
                subsNodes.add(node);
        }
        return subsNodes;
    }

    private ArrayList<HierarchySubtree> orgToOrgSubtree(List<Node> nodeList) {
        ArrayList<HierarchySubtree> hierarchySubtreeArrayList = new ArrayList<>();
        for (Node node : nodeList) {
            hierarchySubtreeArrayList.add(HierarchySubtree.create(node.getId(), node.getName()));
        }
        return hierarchySubtreeArrayList;
    }

}

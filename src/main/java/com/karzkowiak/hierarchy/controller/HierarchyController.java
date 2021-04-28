package com.karzkowiak.hierarchy.controller;

import com.karzkowiak.hierarchy.model.HierarchyTree;
import com.karzkowiak.hierarchy.service.HierarchyTreeService;
import com.karzkowiak.hierarchy.service.NodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
public class HierarchyController {

    private final NodeService organizationService;
    private final HierarchyTreeService orgTreeService;

    HierarchyController(final NodeService organizationService, final HierarchyTreeService orgTreeService) {
        this.organizationService = organizationService;
        this.orgTreeService = orgTreeService;
    }


    @PutMapping(value = "/tree", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity readCSV(@RequestBody String csvData) {
        organizationService.saveCSVdataAsOrgEntities(csvData);
        return ResponseEntity.ok().body("Organizations stored successfully");
    }

    @GetMapping("/tree")
    public ResponseEntity<HierarchyTree> provideOrgTree() {
        return ResponseEntity.ok().body(orgTreeService.createOrgTree());
    }


}
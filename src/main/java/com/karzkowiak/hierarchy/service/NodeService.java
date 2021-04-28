package com.karzkowiak.hierarchy.service;

import com.karzkowiak.hierarchy.repository.NodeRepository;

import org.springframework.stereotype.Service;


@Service
public class NodeService {

    private final NodeRepository repository;
    private final CSVFormatter csvFormatter;

    NodeService(final NodeRepository repository, final CSVFormatter csvFormatter) {
        this.repository = repository;
        this.csvFormatter = csvFormatter;
    }


    public void saveCSVdataAsOrgEntities(String csvData) {
        csvFormatter.formatCSVandSave(csvData, repository);
    }

}

package com.karzkowiak.hierarchy.service;

import com.karzkowiak.hierarchy.model.Node;
import com.karzkowiak.hierarchy.repository.NodeRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Iterator;

@Service
class CSVFormatter {

    void formatCSVandSave(String csvData, NodeRepository repository) {
        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        ColumnPositionMappingStrategy<Node> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Node.class);
        String[] fieldsToBindTo = {"id", "name", "parentId"};
        strategy.setColumnMapping(fieldsToBindTo);
        CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                .withMappingStrategy(strategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<Node> iterator = csvToBean.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            repository.save(node);
        }
    }
}

package com.journal.adib.Journal;

import com.journal.adib.Journal.ErrorHandling.JournalException;
import com.journal.adib.Journal.Models.Database;
import com.journal.adib.Journal.Models.Framework;
import com.journal.adib.Journal.Models.Language;
import com.journal.adib.Journal.Services.DatabaseService;
import com.journal.adib.Journal.Services.FrameworkService;
import com.journal.adib.Journal.Services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "https://mysterious-woodland-86802.herokuapp.com")
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @GetMapping("/databases")
    public ResponseEntity<List<Database>> findAll() throws JournalException {
        return ResponseEntity.status(HttpStatus.OK).body(databaseService.findAll());

    }

    @GetMapping("/databases/{databaseId}")
    public ResponseEntity<Database> findById(@PathVariable(value="databaseId") Long databaseId) throws JournalException {
        return ResponseEntity.status(HttpStatus.OK).body(databaseService.findById(databaseId));
    }

    @PostMapping("/databases")
    public ResponseEntity<Database> create(@Valid @RequestBody Database database) throws JournalException{
        return ResponseEntity.status(HttpStatus.CREATED).body(databaseService.save(database));
    }

    @PutMapping("/databases/{databaseId}")
    public ResponseEntity<Database> update(@PathVariable(value="databaseId") Long databaseId, @Valid @RequestBody Database inputDatabase) throws JournalException{
        Database database = databaseService.findById(databaseId);
        database.setName(inputDatabase.getName());
        return ResponseEntity.status(HttpStatus.OK).body(databaseService.save(database));
    }

    @DeleteMapping("/databases/{databaseId}")
    public void deleteById(@PathVariable(value="databaseId") Long databaseId){
        databaseService.deleteById(databaseId);
    }

}

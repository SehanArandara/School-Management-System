package org.schoolmanagement.studentmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.studentmanagement.models.Mark;
import org.schoolmanagement.studentmanagement.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarkService marksService;



    @PostMapping
    public ResponseEntity<Mark> addMarks(@RequestBody Mark mark) {
        Mark newMark = marksService.addMarks(mark);
        return new ResponseEntity<>(newMark, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mark>> getAllMarks() {
        List<Mark> marksList = marksService.getAllMarks();
        return new ResponseEntity<>(marksList, HttpStatus.OK);
    }

    @GetMapping("/{marksId}")
    public ResponseEntity<Mark> getSingleMarks(@PathVariable String marksId) {
        Mark mark = marksService.getSingleMarks(marksId);
        return new ResponseEntity<>(mark, HttpStatus.OK);
    }

    @PutMapping("/{marksId}")
    public ResponseEntity<Void> updateMarks(@PathVariable String marksId, @RequestBody Mark mark) {
        marksService.updateMarks(marksId, mark);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{marksId}")
    public ResponseEntity<Void> deleteMarks(@PathVariable String marksId) {
        marksService.deleteMarks(marksId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Mark>> getAllMarksBySubjectId(@PathVariable String subjectId) {
        List<Mark> marksList = marksService.getAllMarksBySubjectId(subjectId);
        return new ResponseEntity<>(marksList, HttpStatus.OK);
    }
}

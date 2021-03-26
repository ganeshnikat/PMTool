package com.kanban.pmtool.controllers;

import com.kanban.pmtool.expections.ResourceAlreadyExists;
import com.kanban.pmtool.expections.ResourceNotFoundException;
import com.kanban.pmtool.model.Project;
import com.kanban.pmtool.services.MapValidationErrorService;
import com.kanban.pmtool.services.ProjectService;
import com.kanban.pmtool.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) throws Exception {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        if (Objects.nonNull(projectService.findProjectByProjectIdentifier(project.getProjectIdentifier()))) {
            logger.info("Logger::Project details with projectIdentifier: " + project.getProjectIdentifier() + " is Already Exists!");
            throw new ResourceAlreadyExists("Project details with projectIdentifier: " + project.getProjectIdentifier() + " is Already Exists!");
        }
        return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (Utility.isObjectEmpty(project)) {
            throw new ResourceNotFoundException("Project Details not found for id: " + projectIdentifier.toUpperCase());
        }
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProjects() throws Exception {
        return new ResponseEntity<Iterable<Project>>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("SuccessMessage","Project with projectIdentifier " + projectIdentifier + " was deleted successfully");
        return new ResponseEntity<Map<String,String>>(stringMap, HttpStatus.OK);
    }
}
package com.kanban.pmtool.services;

import com.kanban.pmtool.expections.ResourceNotFoundException;
import com.kanban.pmtool.model.Project;
import com.kanban.pmtool.repositories.ProjectRepository;
import com.kanban.pmtool.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) throws Exception {
        return projectRepository.save(project);
    }

    public Project findProjectByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (Utility.isObjectEmpty(project)) {
            System.out.println("");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() throws Exception {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (Utility.isObjectEmpty(project)) {
            throw new ResourceNotFoundException("Project Details with ProjectIdentifier " + projectIdentifier + " not found");
        }
        projectRepository.delete(project);
    }
}

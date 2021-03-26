package com.kanban.pmtool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Project Name is Required")
    private String projectName;
    @NotBlank(message = "Project Identifier is Required")
    @Size(min = 4, max = 10, message = "please use 4 to 10 character")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Project Description is Required")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateUpdated = new Date();
    }
}

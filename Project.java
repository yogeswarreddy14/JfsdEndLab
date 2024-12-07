package com.klef.jfsd.exam;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "duration")
    private int duration; // in months

    @Column(name = "budget")
    private double budget;

    @Column(name = "team_lead")
    private String teamLead;

    // Constructors, Getters, and Setters
    public Project() {}

    public Project(String projectName, int duration, double budget, String teamLead) {
        this.projectName = projectName;
        this.duration = duration;
        this.budget = budget;
        this.teamLead = teamLead;
    }

    // Getters and Setters...
}

package com.harish.JobApp.job;
//package com.harish.JobApp.company;
import com.harish.JobApp.company.Company;
import jakarta.persistence.*;

@Entity
//@Table(name = "job_table") //default name is same as class
public class Job {
    @Id //specifies that id attribute is the pK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement, takes care of id automatically
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    public Job() { //needed by JPA to instantiate the job entity

    }

    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job(Long id, String title, String description, String minSalary, String location, String maxSalary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public String getLocation() {
        return location;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

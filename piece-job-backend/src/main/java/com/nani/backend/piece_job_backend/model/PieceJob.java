package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@ToString(exclude = {"postedBy"})
@Builder
public class PieceJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String title;
    private String description;
    private String location;
    private double payRate;
    private Date releaseDate;
    private Date expectedEndDate;
    private String specialRequirement;

    @ManyToMany
    @JoinTable(name = "skills_in_jobs",  joinColumns = {@JoinColumn(name = "job_id")},
    inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Business postedBy;

    @OneToMany(mappedBy = "jobPosted")
    private List<PieceJobApplication> jobApplications = new ArrayList<>();

    public void updateToNewPieceJobInformation(PieceJob job) {
        this.title = job.getTitle() == null ? title : job.getTitle();
        this.description = job.getDescription() == null ? description : job.getDescription();
        this.location = job.getLocation()  == null ? location : job.getLocation();
        this.payRate = job.getPayRate();
        this.releaseDate = job.getReleaseDate() == null ? releaseDate : job.getReleaseDate();
        this.expectedEndDate = job.getExpectedEndDate()  == null ? expectedEndDate : job.getExpectedEndDate();
        this.specialRequirement = job.getSpecialRequirement() == null ? specialRequirement : job.getSpecialRequirement();
        this.skills = job.getSkills() == null ? skills : job.getSkills();
        this.postedBy = job.getPostedBy() == null ? postedBy : job.getPostedBy();
    }

    public boolean applyForJob(PieceJobApplication application){
        if (application == null)
            return false ;

        if (jobApplications == null|| jobApplications.isEmpty()){
            jobApplications = new ArrayList<>();
            jobApplications.add(application) ;
            return true ;
        }

        boolean alreadyApplied = jobApplications.stream().anyMatch(i->
                i.getId() == application.getId());
        if(alreadyApplied)
            return false ;
        jobApplications.add(application) ;
//        applicant.applied(this);
        return true;
    }
}

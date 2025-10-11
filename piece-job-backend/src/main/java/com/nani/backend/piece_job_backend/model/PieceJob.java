package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private List<Skill> skills;
    @ManyToOne
    @JsonIgnore
    private Business postedBy;

    @ManyToMany(mappedBy = "jobsApplied")
    private List<Individual> jobApplicants ;

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

    public boolean applyForJob(Individual applicant){
        if (applicant == null)
            return false ;

        if (jobApplicants == null|| jobApplicants.isEmpty()){
            jobApplicants = new ArrayList<>();
            jobApplicants.add(applicant) ;
            return true ;
        }

        boolean alreadyApplied = jobApplicants.stream().anyMatch(i->
                i.getId() == applicant.getId());
        if(alreadyApplied)
            return false ;
        jobApplicants.add(applicant) ;
//        applicant.applied(this);
        return true;
    }
}

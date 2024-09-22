package com.harish.JobApp.job.impl;

import com.harish.JobApp.job.Job;
import com.harish.JobApp.job.JobRepository;
import com.harish.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll(){
        return jobRepository.findAll();
    };

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()){
            Job job  = jobOptional.get();
            job.setId(id);
            job.setDescription(updatedJob.getDescription());
            job.setTitle(updatedJob.getTitle());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJobById(Long id) {
//        return jobs.removeIf(job -> job.getId().equals(id));
        if (jobRepository.existsById(id)) {
            try {
                jobRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
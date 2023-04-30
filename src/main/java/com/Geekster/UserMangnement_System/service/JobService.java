package com.Geekster.UserMangnement_System.service;

import com.Geekster.UserMangnement_System.model.Job;
import com.Geekster.UserMangnement_System.model.JobType;
import com.Geekster.UserMangnement_System.repo.IJobDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    IJobDao jobDao;

    public List<Job> getAll() {
        return (List<Job>) jobDao.findAll();
    }

    public String saveJobs(List<Job> jobs) {
        jobDao.saveAll(jobs);
        return "Added successfully";
    }

    public String updateIfExist(Job job) {
        if(jobDao.existsById(job.getId())){
            jobDao.save(job);
            return "Updated successfully";
        }
        return "No such job with this JobId to update";
    }

    public String deleteJobById(int id) {
        if(jobDao.existsById(id)){
            jobDao.deleteById(id);
            return "Deleted Successfully";
        }return "NO jobs to delete with this Job id";
    }

    public List<Job> getAllJobsByJobType(JobType jobTypeEnum) {
        return jobDao.findByJobType(jobTypeEnum);
    }


    public List<Job> findByTitleContainingIgnoreCase(String title) {
        return jobDao.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> findTop10ByOrderByAppliedDateDesc() {
        return jobDao.findTop10ByOrderByAppliedDateDesc();
    }

    public List<Job> findByCompanyNameContainingIgnoreCase(String companyName) {
        return jobDao.findByCompanyNameContainingIgnoreCase(companyName);
    }

    @Transactional
    public void updateJobTypeById(int id, JobType jobTypeEnum) {
        String jobType= jobTypeEnum.name();
        jobDao.updateJobTypeById(id,jobType);
    }

    @Transactional
    public void updateSalary(int id, Double salary) {
        jobDao.updateSalary(id,salary);
    }

    @Transactional
    public void updateCompanyNameById(int id, String companyName) {
        jobDao.updateCompanyNameById(id,companyName);
    }

    @Transactional
    public void removeJobsLessThanSalary(Double salary) {
        jobDao.removeJobsLessThanSalary(salary);
    }
}
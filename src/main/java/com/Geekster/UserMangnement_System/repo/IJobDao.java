package com.Geekster.UserMangnement_System.repo;

import com.Geekster.UserMangnement_System.model.Job;
import com.Geekster.UserMangnement_System.model.JobType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IJobDao extends CrudRepository<Job,Integer> {

    List<Job> findByJobType(JobType jobType);

    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findTop10ByOrderByAppliedDateDesc();

    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);

    @Modifying
    @Query(value = "UPDATE Job  SET Job_Type = :jobType WHERE id = :id",nativeQuery = true)
    void updateJobTypeById(int id,  String jobType);


    @Modifying
    @Query(value = "update job set Salary=:salary where id=:id",nativeQuery = true)
    void updateSalary(int id, Double salary);

    @Modifying
    @Query(value = "update job set Company_Name=:companyName where id=:id",nativeQuery = true)
    void updateCompanyNameById(int id, String companyName);

    @Modifying
    @Query(value = "Delete from Job where Salary <= :salary",nativeQuery = true)
    void removeJobsLessThanSalary(Double salary);

}

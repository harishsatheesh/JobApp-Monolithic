package com.harish.JobApp.company.impl;

import com.harish.JobApp.company.Company;
import com.harish.JobApp.company.CompanyRepository;
import com.harish.JobApp.company.CompanyService;
import com.harish.JobApp.job.Job;
import com.harish.JobApp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()){
            Company company  = companyOptional.get();
            company.setId(id);
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
//        return jobs.removeIf(job -> job.getId().equals(id));
        if (companyRepository.existsById(id)) {
            try {
                companyRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteAllCompanies() {
        try{companyRepository.deleteAll();
        return true;}
        catch (Exception e){
            return false;
        }
    }
}

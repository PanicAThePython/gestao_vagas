package br.com.nataliaweise.gestaovagas.modules.company.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.nataliaweise.gestaovagas.modules.company.entities.JobEntity;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity job){
        companyRepository.findById(job.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(job);
    }
}

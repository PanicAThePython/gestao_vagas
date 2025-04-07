package br.com.nataliaweise.gestaovagas.modules.candidate.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.JobNotFoundException;
import br.com.nataliaweise.gestaovagas.exceptions.UserNotFoundException;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateRepository;
import br.com.nataliaweise.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import br.com.nataliaweise.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){

        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
        });

        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();
        return applyJobRepository.save(applyJob);
    }
}

package br.com.nataliaweise.gestaovagas.modules.candidate.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.JobNotFoundException;
import br.com.nataliaweise.gestaovagas.exceptions.UserNotFoundException;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateEntity;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateRepository;
import br.com.nataliaweise.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import br.com.nataliaweise.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.nataliaweise.gestaovagas.modules.company.entities.JobEntity;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Impedindo candidatura com id de candidato inválido")
    public void invalid_candidate(){
        try{
            applyJobCandidateUseCase.execute(UUID.randomUUID(), UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Impedindo candidatura com id de vaga inválido")
    public void invalid_job(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(idCandidate, UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Se aplicando à vaga com sucesso")
    public void create_apply_job(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        var idJob = UUID.randomUUID();

        var job = new JobEntity();
        job.setId(idJob);

        Mockito.when(jobRepository.findById(idJob)).thenReturn(Optional.of(job));

        var apply = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        Mockito.when(applyJobRepository.save(apply)).thenReturn(apply);
        applyJobCandidateUseCase.execute(idCandidate, idJob);
    }
}

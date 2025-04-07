package br.com.nataliaweise.gestaovagas.modules.candidate.entity;

import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateEntity;
import br.com.nataliaweise.gestaovagas.modules.company.entities.JobEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_jobs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    @ManyToOne
    private CandidateEntity candidateEntity;

    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    @ManyToOne
    private JobEntity jobEntity;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "job_id")
    private UUID jobId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

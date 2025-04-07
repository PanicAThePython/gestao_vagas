package br.com.nataliaweise.gestaovagas.modules.company.repositories;

import br.com.nataliaweise.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.nataliaweise.gestaovagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}

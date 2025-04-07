package br.com.nataliaweise.gestaovagas.modules.candidate.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.UserNotFoundException;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateRepository;
import br.com.nataliaweise.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .username(candidate.getUsername()).build();
        return candidateDTO;
    }
}

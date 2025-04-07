package br.com.nataliaweise.gestaovagas.modules.candidate.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.UserFoundException;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateEntity;
import br.com.nataliaweise.gestaovagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service //useCase -> camada de serviço, onde tem a regra de negócio
public class CreateCandidateUseCase {

    @Autowired //td q estiver abaixo o spring vai instanciar
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate){
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);
        return this.candidateRepository.save(candidate);
    }
}

package br.com.nataliaweise.gestaovagas.modules.company.useCases;

import br.com.nataliaweise.gestaovagas.exceptions.UserFoundException;
import br.com.nataliaweise.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity company){
        this.companyRepository
                .findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);
        return this.companyRepository.save(company);
    }
}

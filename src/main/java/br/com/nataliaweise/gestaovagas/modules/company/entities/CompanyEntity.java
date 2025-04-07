package br.com.nataliaweise.gestaovagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //gerar uuid aleatório e único de forma automática
    private UUID id;

    @NotBlank
    @Column(name = "nome") //se a coluna e o campo têm nomes difetentes, usa essa nota pra defindir
    private String name;

    @NotBlank
    @Pattern(regexp = "^[^\s]+$", message = "O campo [username] não deve conter espaço") //monta regex
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido") //define mensagem de erro caso n seja tipo email
    private String email;

    //define mensagem de erro caso o tamanho de password n esteja dentro do intervalo definido
    @Length(min = 10, max = 256, message = "O campo [password] deve conter uma senha com tamanho entre 10 e 16")
    private String password;
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

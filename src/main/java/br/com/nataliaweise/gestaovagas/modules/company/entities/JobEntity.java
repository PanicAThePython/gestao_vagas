package br.com.nataliaweise.gestaovagas.modules.company.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //gerar uuid aleatório e único de forma automática
    private UUID id;

    @NotBlank
    @Column(name = "nome") //se a coluna e o campo têm nomes difetentes, usa essa nota pra defindir
    private String name;

    private String description;
    private String benefits;

    @NotBlank(message = "Esse campo é obrigatório")
    private String level;

    //faz o foreign key
    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false) //avisa q esse é só pra conectar as tabelas, o de baixo q vai ser manipulado
    private CompanyEntity company;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

package br.com.nataliaweise.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {

    @Schema(example = "Vaga Dev Frontend", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "Necessário saber React, Redux, Hooks e Typescript.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(example = "Plano Unimed, Gympass, cartão Caju", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String benefits;

    @Schema(example = "Pleno", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}

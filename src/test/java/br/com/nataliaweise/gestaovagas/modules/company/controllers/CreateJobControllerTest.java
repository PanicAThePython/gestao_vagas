package br.com.nataliaweise.gestaovagas.modules.company.controllers;

import br.com.nataliaweise.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.nataliaweise.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.nataliaweise.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.nataliaweise.gestaovagas.modules.company.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.web.context.WebApplicationContext;
import java.util.UUID;
import static br.com.nataliaweise.gestaovagas.utils.TestUtils.generateToken;
import static br.com.nataliaweise.gestaovagas.utils.TestUtils.objectToJSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") //pra definir q tu quer usar o scope test
public class CreateJobControllerTest {

    private MockMvc mvc; //isso simula um server

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Criando uma nova vaga")
    public void should_create_a_new_job() throws Exception {

        var company = CompanyEntity.builder()
                .email("test@test.com")
                .description("description test")
                .name("Company test")
                .password("1234567890")
                .username("company_testname").build();

        company = companyRepository.saveAndFlush(company); //salva imediatamente, sem esperar o fim da requisicao

        var jobDTO = CreateJobDTO.builder()
                .benefits("benefits_test")
                .level("level test")
                .name("name test")
                .description("description test").build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(jobDTO))
                        .header("Authorization", generateToken(company.getId(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result);
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var jobDTO = CreateJobDTO.builder()
                .benefits("benefits_test")
                .level("level test")
                .name("name test")
                .description("description test").build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(jobDTO))
                .header("Authorization", generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // ou result -> assertThat(result.getResponse().getStatus()).isEqualTo(400)
    }
}

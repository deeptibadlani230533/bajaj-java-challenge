package com.bajaj.bfhlapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BfhlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postBfhl_returnsHttp200WithCorrectFields() throws Exception {
        String requestBody = "{\"data\": [\"a\", \"1\", \"334\", \"4\", \"R\", \"$\"]}";

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("deepti_badlani_11032005"))
                .andExpect(jsonPath("$.email").value("deeptibadlani230533@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827CS231074"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }
}

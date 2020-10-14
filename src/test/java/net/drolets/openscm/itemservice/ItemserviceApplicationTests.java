package net.drolets.openscm.itemservice;

import net.drolets.openscm.itemservice.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

class ItemserviceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/api/v1/items/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

package net.drolets.openscm.itemservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.drolets.openscm.itemservice.services.ItemService;
import net.drolets.openscm.itemservice.web.model.ItemDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ItemController.class)

class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    static ItemDto validitem;

    @BeforeAll
    static void setUp() {
        validitem = ItemDto.builder().id(UUID.randomUUID())
                .itemName("item1")
                .description("PALE_ALE")
                .build();
    }

    @Test
    public void getitem() throws Exception {
        given(itemService.getById(any(UUID.class))).willReturn(validitem);

        mockMvc.perform(get("/api/v1/item/" + validitem.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validitem.getId().toString())))
                .andExpect(jsonPath("$.itemName", is("item1")));
    }

    @Test
    public void handlePost() throws Exception {
        //given
        ItemDto itemDto = validitem;
        itemDto.setId(null);
        ItemDto savedDto = ItemDto.builder().id(UUID.randomUUID()).itemName("New item").build();
        String itemDtoJson = objectMapper.writeValueAsString(itemDto);

        given(itemService.saveNewItem(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/item/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        ItemDto itemDto = validitem;
        String itemDtoJson = objectMapper.writeValueAsString(itemDto);

        //when
        mockMvc.perform(put("/api/v1/item/" + validitem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemDtoJson))
                .andExpect(status().isNoContent());

        then(itemService).should().updateItem(any(), any());

    }
}
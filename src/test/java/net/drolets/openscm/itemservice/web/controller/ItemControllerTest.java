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
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ExtendWith(RestDocumentationExtension.class)
//@AutoConfigureRestDocs
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "itemservice.tdrolet.com", uriPort = 443)
@WebMvcTest(controllers = ItemController.class)

class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    static ItemDto validItem;
    static ItemDto[] validItemList;

    @BeforeAll
    static void setUp() {
        validItem = ItemDto.builder().id(UUID.randomUUID())
                .itemName("item1")
                .description("PALE_ALE")
                .build();

        validItemList = new ItemDto[] {
                        ItemDto.builder().id(UUID.randomUUID())
                        .itemName("item1")
                        .description("PALE_ALE")
                        .build(),
                        ItemDto.builder().id(UUID.randomUUID())
                                .itemName("item2")
                                .description("PILSNER")
                                .build(),
                        ItemDto.builder().id(UUID.randomUUID())
                                .itemName("item3")
                                .description("STOUT")
                                .build()};

    }

    @Test
    public void getitem() throws Exception {
        given(itemService.getById(any(UUID.class))).willReturn(validItem);

        mockMvc.perform(get("/api/v1/items/{itemId}", validItem.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validItem.getId().toString())))
                .andExpect(jsonPath("$.itemName", is("item1")))
                .andDo(document("v1/items-getbyid",
                            pathParameters(
                                parameterWithName("itemId").description("UUID of desired Item to get.")),
                            responseFields(
                                fieldWithPath("id").description("Id of Item."),
                                fieldWithPath("itemName").description("Name of Item."),
                                fieldWithPath("description").description("Description of Item.")
                            )
                        )
                );
    }

    @Test
    public void getitems() throws Exception {
        given(itemService.getItems(100,0)).willReturn(validItemList);

        mockMvc.perform(get("/api/v1/items/"))
                .andExpect(status().isOk())
                .andDo(document("v1/items-get",
                            requestParameters(
                                        parameterWithName("limit").optional().description("Max number of Items to return, optional, default = 100"),
                                        parameterWithName("offset").optional().description("Offset into Items result (for paging), optional, default = 0")
                            ),
                            responseFields(
                                    fieldWithPath("[]").description("An array of Items"),
                                    fieldWithPath("[].id").description("Id of Item."),
                                    fieldWithPath("[].itemName").description("Name of Item."),
                                    fieldWithPath("[].description").description("Description of Item.")
                            )
                        )
                );
    }

    @Test
    public void handlePost() throws Exception {
        //given
        ItemDto itemDto = validItem;
        itemDto.setId(null);
        ItemDto savedDto = ItemDto.builder().id(UUID.randomUUID()).itemName("New Item").build();
        String itemDtoJson = objectMapper.writeValueAsString(itemDto);

        ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(ItemDto.class);

        given(itemService.saveNewItem(any())).willReturn(savedDto);

        List<String> s = constraintDescriptions.descriptionsForProperty("itemName");

        mockMvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/items-post",
                            requestFields(
                                    fieldWithPath("id").ignored(),
                                    fieldWithPath("itemName")
                                        .description("Name of the Item.")
                                        .attributes(key("constraints")
                                                .value(constraintDescriptions.descriptionsForProperty("itemName"))),
                                    fieldWithPath("description").description("Description of the Item.")
                                        .attributes(key("constraints")
                                                .value(constraintDescriptions.descriptionsForProperty("description")))
                            )
                        )
                );



    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        ItemDto itemDto = validItem;
        String itemDtoJson = objectMapper.writeValueAsString(itemDto);

        ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(ItemDto.class);

        //when
        mockMvc.perform(put("/api/v1/items/" + validItem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemDtoJson))
                .andExpect(status().isNoContent())
                .andDo(document("v1/items-put",
                            requestFields(
                                    fieldWithPath("id").ignored(),
                                    fieldWithPath("itemName")
                                            .description("Name of the Item.")
                                            .attributes(key("constraints")
                                                    .value(constraintDescriptions.descriptionsForProperty("itemName"))),
                                    fieldWithPath("description").description("Description of the Item.")
                                            .attributes(key("constraints")
                                                    .value(constraintDescriptions.descriptionsForProperty("description")))
                            )
                        )
                );

        then(itemService).should().updateItem(any(), any());

    }

    @Test
    public void deleteitem() throws Exception {


        mockMvc.perform(delete("/api/v1/items/{itemId}", validItem.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(document("v1/items-delete",
                                    pathParameters(
                                            parameterWithName("itemId").description("UUID of desired Item to delete.")
                                    )
                                )
                );
    }
}
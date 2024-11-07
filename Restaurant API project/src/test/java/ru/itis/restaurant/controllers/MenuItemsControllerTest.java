package ru.itis.restaurant.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.restaurant.dto.MenuItemDto;
import ru.itis.restaurant.dto.MenuItemsPage;
import ru.itis.restaurant.security.details.AccountUserDetailsService;
import ru.itis.restaurant.services.MenuItemsService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemsController.class)
@DisplayName("Menu items controller is working when:")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MenuItemsControllerTest {
    private static final MenuItemDto PIZZA_MEAT_ITEM = MenuItemDto.builder()
            .id(1L).title("pizza ultra meat").description("4 types of meat").weight(490).price(370).category("DISH").build();

    private static final MenuItemDto MILKSHAKE_BANANA_ITEM = MenuItemDto.builder()
            .id(2L).title("banana milkshake").description("fresh bananas").weight(500).price(290).category("DRINK").build();

    private static final List<MenuItemDto> MENU_ITEM_LIST = Arrays.asList(PIZZA_MEAT_ITEM, MILKSHAKE_BANANA_ITEM);

    private static final MenuItemDto NEW_ITEM = MenuItemDto.builder()
            .id(4L).title("pizza diablo").description("ultra hot").weight(480).price(320).category("DISH").build();

    private static final MenuItemDto CREATED_ITEM = MenuItemDto.builder()
            .id(4L).title("pizza diablo").description("ultra hot").weight(480).price(320).category("DISH").build();

    @Autowired
    private MockMvc mockMvc;

    private String token = "";

    @MockBean
    private MenuItemsService menuItemsService;

    @MockBean
    private AccountUserDetailsService accountUserDetailsService;

    @BeforeEach
    void setUp() {
        when(menuItemsService.getMenuItems(0)).thenReturn(MenuItemsPage.builder()
                .menuItems(MENU_ITEM_LIST).totalPages(1).build());

    }

    @Test
    void return_menu_items_on_0_page()  throws Exception {
        mockMvc.perform(get("/menu")
                        .param("page", "0")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("menuItems", hasSize(2)))
                .andExpect(jsonPath("totalPages", is(1)))
                .andExpect(jsonPath("menuItems[0].title", is("pizza ultra meat")))
                .andExpect(jsonPath("menuItems[0].category", is("DISH")))
                .andExpect(jsonPath("menuItems[1].title", is("banana milkshake")))
                .andExpect(jsonPath("menuItems[1].category", is("DRINK")));

    }

    @Test
    public void return_403_without_token() throws Exception {
        mockMvc.perform(get("/menu")
                        .param("page", "1"))
                .andExpect(status().isForbidden());
    }


    @Test
    void return_400_when_add_item_with_invalid_category() throws Exception {
        mockMvc.perform(post("/menu")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                " \"title\": \"pizza diablo\", \n" +
                                " \"description\": \"ultra hot\", \n" +
                                " \"weight\": \"480\", \n" +
                                " \"price\": \"320 \", \n" +
                                " \"category\": \"PIZZA\" \n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void add_item_with_valid_fields() throws Exception {
        mockMvc.perform(post("/menu")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                " \"title\": \"pizza diablo\", \n" +
                                " \"description\": \"ultra hot\", \n" +
                                " \"weight\": \"480\", \n" +
                                " \"price\": \"320 \", \n" +
                                " \"category\": \"DISH\" \n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title", is("pizza diablo")))
                .andExpect(jsonPath("category", is("DISH")))
                .andDo(print());
    }

}
package com.me.ControllerTest;

import com.me.controllers.UserFavouritesController;
import com.me.models.entities.MyFavourite;
import com.me.services.FavouriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserFavouritesController.class)
public class UserFavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavouriteService favouriteService;

    private final String baseUrl = "/favourites";

    @Test
    void whenGetFavouritesByUserName_withValidUser_shouldReturnFavourites() throws Exception {
        
        String userName = "testUser";
        ArrayList<MyFavourite> expectedFavourites = new ArrayList<>();
        MyFavourite favourite = new MyFavourite();
        favourite.setUserName(userName);
        expectedFavourites.add(favourite);

        when(favouriteService.getFavouriteByUserName(userName)).thenReturn(expectedFavourites);

        
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value(userName));
    }

    @Test
    void whenGetAllFavourites_shouldReturnAllFavourites() throws Exception {
        
        ArrayList<MyFavourite> expectedFavourites = new ArrayList<>();
        expectedFavourites.add(new MyFavourite());

        when(favouriteService.getAllFavourites()).thenReturn(expectedFavourites);

        
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void whenGetFans_withValidRequest_shouldReturnFans() throws Exception {
        
        ArrayList<String> fans = new ArrayList<>();
        fans.add("fan1");
        fans.add("fan2");

        when(favouriteService.getFans(any())).thenReturn(fans);

        
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"song\":\"testSong\",\"artist\":\"testArtist\",\"userName\":\"testUser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void whenCreateFavourite_withNewFavourite_shouldReturnCreated() throws Exception {

        when(favouriteService.createFavourite(any())).thenReturn(HttpStatus.CREATED);


        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"song\":\"newSong\",\"artist\":\"newArtist\",\"userName\":\"newUser\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateFavourite_withExistingFavourite_shouldReturnCreated() throws Exception {

        when(favouriteService.updateFavourite(any())).thenReturn(HttpStatus.CREATED);


        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"song\":\"existingSong\",\"artist\":\"existingArtist\",\"userName\":\"existingUser\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteFavourite_withValidId_shouldReturnNoContent() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/delete/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetFans_withInvalidRequest_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
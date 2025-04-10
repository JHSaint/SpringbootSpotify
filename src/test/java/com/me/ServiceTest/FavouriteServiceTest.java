package com.me.ServiceTest;

import com.me.models.Favourite;
import com.me.models.NewFavouriteRequest;
import com.me.models.entities.MyFavourite;
import com.me.models.repositories.SpotifyFaveRepository;
import com.me.services.FavouriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavouriteServiceTest {

    @Mock
    private SpotifyFaveRepository spotifyFaveRepository;

    @InjectMocks
    private FavouriteService favouriteService;

    private MyFavourite testFavourite;
    private Favourite myTestFavourite;
    private NewFavouriteRequest testFavouriteRequest;
    private final String testUserName = "testUser";
    private final String testSong = "Test Song";
    private final String testArtist = "Test Artist";

    @BeforeEach
    void setUp() {
        testFavourite = new MyFavourite();
        testFavourite.setId(1L);
        testFavourite.setUserName(testUserName);
        testFavourite.setSong(testSong);
        testFavourite.setArtist(testArtist);

        myTestFavourite = new Favourite();
        myTestFavourite.setUserName(testUserName);
        myTestFavourite.setSong(testSong);
        myTestFavourite.setArtist(testArtist);

        testFavouriteRequest = new NewFavouriteRequest();
        testFavouriteRequest.setUserName(testUserName);
        testFavouriteRequest.setSong(testSong);
        testFavouriteRequest.setArtist(testArtist);
    }

    @Test
    void whenGetFavouriteByUserName_withValidUser_shouldReturnFavourites() {
        
        List<MyFavourite> expectedFavourites = List.of(testFavourite);
        when(spotifyFaveRepository.findAllByUserName(testUserName)).thenReturn(new ArrayList<>(expectedFavourites));
        ArrayList<MyFavourite> result = favouriteService.getFavouriteByUserName(testUserName);
        assertEquals(1, result.size());
        assertEquals(testSong, result.get(0).getSong());
        verify(spotifyFaveRepository).findAllByUserName(testUserName);
    }

    @Test
    void whenGetFavouriteByUserName_withException_shouldReturnEmptyList() {
        
        when(spotifyFaveRepository.findAllByUserName(testUserName)).thenThrow(new RuntimeException("DB Error"));
        ArrayList<MyFavourite> result = favouriteService.getFavouriteByUserName(testUserName);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenGetAllFavourites_withData_shouldReturnAllFavourites() {
        
        List<MyFavourite> expectedFavourites = List.of(testFavourite);
        when(spotifyFaveRepository.findAll()).thenReturn(new ArrayList<>(expectedFavourites));
        ArrayList<MyFavourite> result = favouriteService.getAllFavourites();
        assertEquals(1, result.size());
        verify(spotifyFaveRepository).findAll();
    }

    @Test
    void whenGetFans_withValidSongAndArtist_shouldReturnFans() {
        
        List<MyFavourite> fans = List.of(testFavourite);
        when(spotifyFaveRepository.findAllBySongAndArtist(testSong, testArtist))
                .thenReturn(new ArrayList<>(fans));

        ArrayList<String> result = favouriteService.getFans(myTestFavourite);
        assertEquals(1, result.size());
        assertEquals(testUserName, result.get(0));
    }

    @Test
    void whenCreateFavourite_withNewSong_shouldReturnCreated() {
        
        when(spotifyFaveRepository.existsBySongId(anyString())).thenReturn(false);
        when(spotifyFaveRepository.save(any(MyFavourite.class))).thenReturn(testFavourite);
        HttpStatus result = favouriteService.createFavourite(testFavouriteRequest);
        assertEquals(HttpStatus.CREATED, result);
        verify(spotifyFaveRepository).save(any(MyFavourite.class));
    }

    @Test
    void whenCreateFavourite_withExistingSong_shouldReturnCreated() {
        
        when(spotifyFaveRepository.existsBySongId(anyString())).thenReturn(true);
        HttpStatus result = favouriteService.createFavourite(testFavouriteRequest);
        assertEquals(HttpStatus.CREATED, result);
        verify(spotifyFaveRepository, never()).save(any());
    }

    @Test
    void whenCreateFavourite_withException_shouldReturnInternalServerError() {
        
        when(spotifyFaveRepository.existsBySongId(anyString())).thenThrow(new RuntimeException("DB Error"));
        HttpStatus result = favouriteService.createFavourite(testFavouriteRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }

    @Test
    void whenDeleteFavourite_withValidId_shouldDelete() {
        
        when(spotifyFaveRepository.findById(1L)).thenReturn(Optional.of(testFavourite));
        favouriteService.deleteFavouriteById(1L);
        verify(spotifyFaveRepository).delete(testFavourite);
    }

    @Test
    void whenDeleteFavourite_withInvalidId_shouldNotDelete() {
        
        when(spotifyFaveRepository.findById(1L)).thenReturn(Optional.empty());
        favouriteService.deleteFavouriteById(1L);
        verify(spotifyFaveRepository, never()).delete(any());
    }

    @Test
    void calculateSongId_shouldReturnCorrectFormat() {
        
        String result = favouriteService.calculateSongId(testSong, testUserName);
        assertEquals(testSong + testUserName, result);
    }
}
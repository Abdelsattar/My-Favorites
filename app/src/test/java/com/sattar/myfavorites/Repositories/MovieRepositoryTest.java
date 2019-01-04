package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieRepositoryTest {

    MovieRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new MovieRepository();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertMovies() {
        repository.insertMovies(new Movie());

        //verify that the movies already inserted
    }
}
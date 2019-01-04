package com.sattar.myfavorites.Repositories;

import android.support.test.InstrumentationRegistry;

import com.sattar.myfavorites.Models.Movie;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MoviesRepositoryTest {

    private MovieRepository repository;
    Realm testRealm;

    @Before
    public void setUp() throws Exception {
        repository = new MovieRepository();

        Realm.init(InstrumentationRegistry.getTargetContext());
        RealmConfiguration testConfig =
                new RealmConfiguration.Builder().
                        inMemory().
                        name("test-realm").build();
         testRealm = Realm.getInstance(testConfig);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertMovies() {
        repository.insertMovies(
                testRealm,
                "X-Men",
                "a science fiction movie",
                "url",
                8.6);

        //verify that the movies already inserted
        Assert.assertEquals(1,testRealm.where(Movie.class).findAll().size());
    }
}
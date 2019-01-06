package com.sattar.myfavorites.di;


import com.sattar.myfavorites.Views.Activites.MainActivity;

import androidx.lifecycle.ViewModel;
import dagger.Component;

@Component(modules = {AppModule.class, ViewModelModule.class})
public interface AppCompetent {

    void inject(MainActivity main);
    void inject(ViewModel viewModel);

}

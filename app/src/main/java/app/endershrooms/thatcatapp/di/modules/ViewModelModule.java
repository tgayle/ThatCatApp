package app.endershrooms.thatcatapp.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import app.endershrooms.thatcatapp.di.ViewModelKey;
import app.endershrooms.thatcatapp.screens.CatViewModelFactory;
import app.endershrooms.thatcatapp.screens.fragment.breeds.CatBreedViewModel;
import app.endershrooms.thatcatapp.screens.fragment.favorites.CatFavoritesViewModel;
import app.endershrooms.thatcatapp.screens.fragment.info.CatInfoViewModel;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchViewModel;
import app.endershrooms.thatcatapp.screens.fragment.vote.CatVoteViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import javax.inject.Singleton;

@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(CatBreedViewModel.class)
  abstract ViewModel bindCatBreedViewModel(CatBreedViewModel breedVm);

  @Binds
  @IntoMap
  @ViewModelKey(CatFavoritesViewModel.class)
  abstract ViewModel bindCatFavoritesViewModel(CatFavoritesViewModel favVm);

  @Binds
  @IntoMap
  @ViewModelKey(CatSearchViewModel.class)
  abstract ViewModel bindCatSearchViewModel(CatSearchViewModel searchViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(CatVoteViewModel.class)
  abstract ViewModel bindCatVoteViewModel(CatVoteViewModel voteViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(CatInfoViewModel.class)
  abstract ViewModel bindCatInfoViewModel(CatInfoViewModel infoViewModel);

  @Binds
  @Singleton
  abstract ViewModelProvider.Factory bindViewModelFactory(CatViewModelFactory factory);
}

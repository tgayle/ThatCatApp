package app.endershrooms.thatcatapp.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.di.ViewModelKey;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.CatViewModelFactory;
import app.endershrooms.thatcatapp.screens.fragment.breeds.CatBreedViewModel;
import app.endershrooms.thatcatapp.screens.fragment.favorites.CatFavoritesViewModel;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchViewModel;
import app.endershrooms.thatcatapp.screens.fragment.vote.CatVoteViewModel;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import java.util.Map;
import javax.inject.Provider;
import javax.inject.Singleton;

@Module
public class ViewModelModule {

  @Provides
  @IntoMap
  @ViewModelKey(CatBreedViewModel.class)
  ViewModel provideCatBreedViewModel(BreedDao breedDao) {
    return new CatBreedViewModel(breedDao);
  }

  @Provides
  @IntoMap
  @ViewModelKey(CatFavoritesViewModel.class)
  ViewModel provideCatFavoritesViewModel() {
    return new CatFavoritesViewModel();
  }

  @Provides
  @IntoMap
  @ViewModelKey(CatSearchViewModel.class)
  ViewModel bindCatSearchViewModel(CatService catService) {
    return new CatSearchViewModel(catService);
  }

  @Provides
  @IntoMap
  @ViewModelKey(CatVoteViewModel.class)
  ViewModel bindCatVoteViewModel() {
    return new CatVoteViewModel();
  }

  @Provides
  @Singleton
  ViewModelProvider.Factory bindViewModelFactory(
      Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    return new CatViewModelFactory(providerMap);
  }
}

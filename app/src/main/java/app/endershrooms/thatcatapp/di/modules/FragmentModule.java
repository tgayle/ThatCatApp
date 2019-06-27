package app.endershrooms.thatcatapp.di.modules;

import app.endershrooms.thatcatapp.screens.fragment.breeds.CatBreedsFragment;
import app.endershrooms.thatcatapp.screens.fragment.favorites.CatFavoritesFragment;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchFragment;
import app.endershrooms.thatcatapp.screens.fragment.vote.CatVoteFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

  @ContributesAndroidInjector
  abstract CatBreedsFragment contributeCatBreedsFragment();

  @ContributesAndroidInjector
  abstract CatFavoritesFragment contributeCatFavoritesFragment();

  @ContributesAndroidInjector
  abstract CatSearchFragment contributeCatSearchFragment();

  @ContributesAndroidInjector
  abstract CatVoteFragment contributeCatVoteFragment();
}

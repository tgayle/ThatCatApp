package app.endershrooms.thatcatapp.di.modules;

import app.endershrooms.thatcatapp.screens.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

  @ContributesAndroidInjector(modules = FragmentModule.class)
  abstract MainActivity contributeMainActivity();
}

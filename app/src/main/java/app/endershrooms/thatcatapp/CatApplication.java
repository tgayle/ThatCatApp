package app.endershrooms.thatcatapp;

import android.app.Activity;
import android.app.Application;
import app.endershrooms.thatcatapp.di.component.DaggerCatAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

public class CatApplication extends Application implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override
  public void onCreate() {
    DaggerCatAppComponent.builder()
        .application(this)
        .build()
        .inject(this);
    super.onCreate();
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}

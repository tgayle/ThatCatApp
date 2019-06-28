package app.endershrooms.thatcatapp.di.component;

import android.app.Application;
import app.endershrooms.thatcatapp.CatApplication;
import app.endershrooms.thatcatapp.db.CatDatabase;
import app.endershrooms.thatcatapp.di.modules.ActivityModule;
import app.endershrooms.thatcatapp.di.modules.AppModule;
import app.endershrooms.thatcatapp.net.CatService;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Component(
    modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
    })
@Singleton
public interface CatAppComponent {

  CatDatabase getCatDatabase();
  CatService getCatService();
  void inject(CatApplication catApp);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    CatAppComponent build();
  }
}

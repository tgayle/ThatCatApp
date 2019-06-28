package app.endershrooms.thatcatapp.di.modules;

import app.endershrooms.thatcatapp.net.CatService;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = {ViewModelModule.class, DatabaseModule.class})
public class AppModule {

  @Singleton
  @Provides
  Moshi provideMoshi() {
    return new Moshi.Builder().build();
  }

  @Singleton
  @Provides
  CatService provideCatService(Moshi moshi) {
    return new Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(CatService.class);
  }
}

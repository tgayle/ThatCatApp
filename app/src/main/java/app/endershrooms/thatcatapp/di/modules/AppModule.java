package app.endershrooms.thatcatapp.di.modules;

import app.endershrooms.thatcatapp.net.CatService;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
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
  CatService provideCatService(Moshi moshi, OkHttpClient client) {
    return new Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(CatService.class);
  }

  @Singleton
  @Provides
  OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BASIC))
        .build();
  }
}

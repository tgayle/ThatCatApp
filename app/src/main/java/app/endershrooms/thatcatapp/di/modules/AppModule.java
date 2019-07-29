package app.endershrooms.thatcatapp.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import app.endershrooms.thatcatapp.model.helper.BreedsToBreedIdsAdapter;
import app.endershrooms.thatcatapp.model.helper.IntToBooleanAdapter;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.util.Constants;
import app.endershrooms.thatcatapp.util.UserInfo;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    return new Moshi.Builder()
        .add(new IntToBooleanAdapter())
        .add(new BreedsToBreedIdsAdapter())
        .build();
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
        .addInterceptor(chain -> {
          Request request = chain.request();
          request = request.newBuilder()
              .addHeader("x-api-key", Constants.API_KEY)
              .build();
          return chain.proceed(request);
        })
        .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BASIC))
        .build();
  }

  @Singleton
  @Provides
  UserInfo provideUserInfo(SharedPreferences sharedPrefs) {
    return new UserInfo(sharedPrefs);
  }

  @Singleton
  @Provides
  SharedPreferences provideSharedPreferences(Application application) {
    return application.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
  }
}

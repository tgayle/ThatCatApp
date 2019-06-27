package app.endershrooms.thatcatapp.di.modules;

import android.app.Application;
import androidx.room.Room;
import app.endershrooms.thatcatapp.db.CatDatabase;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.db.dao.CategoryDao;
import app.endershrooms.thatcatapp.db.dao.FavoriteDao;
import app.endershrooms.thatcatapp.db.dao.VoteDao;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DatabaseModule {

  @Singleton
  @Provides
  CatDatabase provideDatabase(Application app) {
    return Room
        .databaseBuilder(app, CatDatabase.class, "cat.db")
        .fallbackToDestructiveMigration()
        .build();
  }

  @Singleton
  @Provides
  BreedDao provideBreedDao(CatDatabase db) {
    return db.breedDao();
  }

  @Singleton
  @Provides
  VoteDao provideVoteDao(CatDatabase db) {
    return db.voteDao();
  }

  @Singleton
  @Provides
  CategoryDao provideCategoryDao(CatDatabase db) {
    return db.categoryDao();
  }

  @Singleton
  @Provides
  FavoriteDao provideFavoriteDao(CatDatabase db) {
    return db.favoriteDao();
  }
}

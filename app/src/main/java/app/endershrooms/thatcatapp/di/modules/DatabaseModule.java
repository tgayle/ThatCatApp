package app.endershrooms.thatcatapp.di.modules;

import android.app.Application;
import androidx.room.Room;
import app.endershrooms.thatcatapp.db.CatDatabase;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.db.dao.CatDao;
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

  @Provides
  BreedDao provideBreedDao(CatDatabase db) {
    return db.breedDao();
  }

  @Provides
  VoteDao provideVoteDao(CatDatabase db) {
    return db.voteDao();
  }

  @Provides
  CategoryDao provideCategoryDao(CatDatabase db) {
    return db.categoryDao();
  }

  @Provides
  FavoriteDao provideFavoriteDao(CatDatabase db) {
    return db.favoriteDao();
  }

  @Provides
  CatDao provideCatDao(CatDatabase db) {
    return db.catDao();
  }
}

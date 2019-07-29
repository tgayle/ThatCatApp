package app.endershrooms.thatcatapp.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.db.dao.CatDao;
import app.endershrooms.thatcatapp.db.dao.CategoryDao;
import app.endershrooms.thatcatapp.db.dao.FavoriteDao;
import app.endershrooms.thatcatapp.db.dao.VoteDao;
import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.model.Category;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.model.Vote;

@Database(
    version = 2,
    entities = {
        Category.class, Breed.class,
        Vote.class, Favorite.class, Cat.class
    },
    exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class CatDatabase extends RoomDatabase {

  public abstract CategoryDao categoryDao();

  public abstract VoteDao voteDao();

  public abstract BreedDao breedDao();

  public abstract FavoriteDao favoriteDao();

  public abstract CatDao catDao();

  public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("CREATE TABLE `Cat` (`id` TEXT NOT NULL, `breedIds` TEXT, `url` TEXT, `width` INTEGER, `height` INTEGER, PRIMARY KEY (`id`))");
    }
  };
}

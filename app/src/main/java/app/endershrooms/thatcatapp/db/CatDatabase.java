package app.endershrooms.thatcatapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.db.dao.CatDao;
import app.endershrooms.thatcatapp.db.dao.CategoryDao;
import app.endershrooms.thatcatapp.db.dao.FavoriteDao;
import app.endershrooms.thatcatapp.db.dao.VoteDao;
import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.model.Category;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.model.Vote;

@Database(
    version = 1,
    entities = {
        Category.class, Breed.class,
        Vote.class, Favorite.class,
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
}

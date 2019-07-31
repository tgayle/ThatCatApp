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
    version = 3,
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

  public static final Migration MIGRATION_2_3 = new Migration(2,3) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE `Cat` RENAME TO _Cat_old");
      database.execSQL("CREATE TABLE `Cat` (`catId` TEXT NOT NULL, `breedIds` TEXT, `url` TEXT, `width` INTEGER, `height` INTEGER, PRIMARY KEY (`catId`))");
      database.execSQL("INSERT INTO `Cat` SELECT * FROM _Cat_old");
      database.execSQL("DROP TABLE _Cat_old");

      database.execSQL("ALTER TABLE `Breed` RENAME TO _Breed_old");
      database.execSQL("CREATE TABLE `Breed` (`weight` TEXT, `breedId` TEXT NOT NULL, `name` TEXT, `cfaUrl` TEXT, `vetstreetUrl` TEXT, `vcahospitalsUrl` TEXT, `temperament` TEXT, `origin` TEXT, `countryCodes` TEXT, `countryCode` TEXT, `description` TEXT, `lifeSpan` TEXT, `indoor` INTEGER, `lap` INTEGER, `altNames` TEXT, `adaptability` INTEGER, `affectionLevel` INTEGER, `childFriendly` INTEGER, `dogFriendly` INTEGER, `energyLevel` INTEGER, `grooming` INTEGER, `healthIssues` INTEGER, `intelligence` INTEGER, `sheddingLevel` INTEGER, `socialNeeds` INTEGER, `strangerFriendly` INTEGER, `vocalisation` INTEGER, `experimental` INTEGER NOT NULL, `hairless` INTEGER NOT NULL, `natural` INTEGER NOT NULL, `rare` INTEGER NOT NULL, `rex` INTEGER NOT NULL, `suppressedTail` INTEGER NOT NULL, `shortLegs` INTEGER NOT NULL, `wikipediaUrl` TEXT, `hypoallergenic` INTEGER NOT NULL, PRIMARY KEY(`breedId`))");
      database.execSQL("INSERT INTO `Breed` SELECT * FROM _Breed_old");
      database.execSQL("DROP TABLE _Breed_old");
    }
  };
}

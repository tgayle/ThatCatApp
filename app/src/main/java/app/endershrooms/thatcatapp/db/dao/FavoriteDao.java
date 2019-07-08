package app.endershrooms.thatcatapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import app.endershrooms.thatcatapp.model.Favorite;
import java.util.List;

@Dao
public interface FavoriteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertFavorites(List<Favorite> favorites);

  @Query("SELECT * FROM favorite")
  LiveData<List<Favorite>> getFavorites();
}

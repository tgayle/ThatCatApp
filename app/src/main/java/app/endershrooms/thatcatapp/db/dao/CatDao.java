package app.endershrooms.thatcatapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import app.endershrooms.thatcatapp.model.Cat;
import java.util.List;

@Dao
public interface CatDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Cat... cats);

  @Query("SELECT * FROM Cat")
  LiveData<List<Cat>> getAllCats();

}

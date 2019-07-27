package app.endershrooms.thatcatapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import app.endershrooms.thatcatapp.model.Breed;
import java.util.List;

@Dao
public interface BreedDao {

  @Query("SELECT * FROM breed ORDER BY name ASC")
  LiveData<List<Breed>> getBreeds();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insertBreeds(List<Breed> breeds);

}

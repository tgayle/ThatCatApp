package app.endershrooms.thatcatapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import app.endershrooms.thatcatapp.model.Category;
import java.util.List;

@Dao
public interface CategoryDao {

  @Query("SELECT * FROM category ORDER BY id")
  LiveData<List<Category>> getCategories();

  @Query("SELECT * FROM category WHERE id = :id")
  LiveData<Category> getCategory(int id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategories(List<Category> categories);
}

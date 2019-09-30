package app.endershrooms.thatcatapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.endershrooms.thatcatapp.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category ORDER BY id")
    fun getCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategory(id: Int): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<Category>)
}

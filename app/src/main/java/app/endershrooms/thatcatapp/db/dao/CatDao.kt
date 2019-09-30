package app.endershrooms.thatcatapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.endershrooms.thatcatapp.model.Cat

@Dao
interface CatDao {

    @Query("SELECT * FROM Cat")
    fun getAllCats(): LiveData<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg cats: Cat)

}

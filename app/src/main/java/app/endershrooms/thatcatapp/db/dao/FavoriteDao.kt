package app.endershrooms.thatcatapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.endershrooms.thatcatapp.model.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getFavorites(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(favorites: List<Favorite>)

    @Query("SELECT * FROM favorite WHERE imageId = :imageId LIMIT 1")
    fun getFavoriteByImageId(imageId: String): LiveData<Favorite>
}

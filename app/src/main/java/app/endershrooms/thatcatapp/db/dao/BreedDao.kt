package app.endershrooms.thatcatapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.endershrooms.thatcatapp.model.Breed
import app.endershrooms.thatcatapp.model.BreedWithExampleCat

@Dao
interface BreedDao {

    @Query("SELECT * FROM breed ORDER BY name ASC")
    fun getBreeds(): LiveData<List<Breed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreeds(breeds: List<Breed>): List<Long>

    @Query("SELECT * FROM Breed LEFT JOIN Cat ON Cat.breedIds LIKE Breed.breedId WHERE breedId = :breedId LIMIT 1")
    fun getBreedWithPreviewCat(breedId: String): LiveData<BreedWithExampleCat>
}

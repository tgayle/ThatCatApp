package app.endershrooms.thatcatapp.model.helper

import app.endershrooms.thatcatapp.model.Breed
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class BreedsToBreedIdsAdapter {
    @FromJson
    @BreedsToBreedId
    fun fromJson(breeds: List<Breed>): List<String> = breeds.map { it.breedId }

    /*
      Return an empty list here as it's not very easy to convert the ids
      back to the breeds without a database query.
   */
    @ToJson
    fun toJson(@BreedsToBreedId breedIds: List<String>): List<Breed> = emptyList()
}

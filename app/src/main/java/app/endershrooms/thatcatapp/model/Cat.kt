package app.endershrooms.thatcatapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.endershrooms.thatcatapp.model.helper.BreedsToBreedId
import com.squareup.moshi.Json


@Entity
data class Cat(
    @NonNull
    @PrimaryKey
    @field:Json(name = "id")
    var catId: String,

    @Json(name = "breeds")
    @BreedsToBreedId
    var breedIds: List<String>,

    @Json(name = "url")
    var url: String?,

    @Json(name = "height")
    var height: Int?,

    @Json(name = "width")
    var width: Int?
)
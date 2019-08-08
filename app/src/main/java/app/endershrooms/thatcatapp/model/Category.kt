package app.endershrooms.thatcatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Category(
    @Json(name = "id") @PrimaryKey var id: Int?,
    @Json(name = "name") var name: String?
)
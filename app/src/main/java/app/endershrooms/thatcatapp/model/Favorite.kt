package app.endershrooms.thatcatapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Favorite(
    @PrimaryKey
    @field:Json(name = "id")
    @ColumnInfo(name = "fav_id")
    var id: Int?,

    @field:Json(name = "user_id")
    var userId: String?,

    @field:Json(name = "image_id")
    var imageId: String?,

    @field:Json(name = "sub_id")
    var subId: String?,

    @field:Json(name = "created_at")
    var createdAt: String?,

    @Embedded @Json(name = "image") var image: Image?
)
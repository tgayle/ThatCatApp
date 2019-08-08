package app.endershrooms.thatcatapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Favorite(
    @PrimaryKey @Json(name = "id") @ColumnInfo(name = "fav_id") var id: Int?,
    @Json(name = "user_id")         var userId: String?,
    @Json(name = "image_id")        var imageId: String?,
    @Json(name = "sub_id")          var subId: String?,
    @Json(name = "created_at")      var createdAt: String?,
    @Embedded @Json(name = "image") var image: Image?
)
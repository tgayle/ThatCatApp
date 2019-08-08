package app.endershrooms.thatcatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Vote(
    @PrimaryKey @Json(name = "id") var id: Int?,
    @Json(name = "image_id") var imageId: String?,
    @Json(name = "sub_id") var subId: String?,
    @Json(name = "created_at") var createdAt: String?,
    @Json(name = "value") var value: Int?,
    @Json(name = "country_code") var countryCode: String?
)
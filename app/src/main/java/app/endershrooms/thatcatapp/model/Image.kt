package app.endershrooms.thatcatapp.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json

data class Image(
    @Json(name = "id") @ColumnInfo(name = "img_id") var id: String?,
    @Json(name = "url") var url: String? = null
)
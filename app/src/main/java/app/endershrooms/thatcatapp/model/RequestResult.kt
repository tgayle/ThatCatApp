package app.endershrooms.thatcatapp.model

import com.squareup.moshi.Json

data class RequestResult(
    @Json(name = "message") var message: String?,
    @Json(name = "id") var id: Int?
)
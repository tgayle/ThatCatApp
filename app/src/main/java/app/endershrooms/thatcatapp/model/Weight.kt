package app.endershrooms.thatcatapp.model

import com.squareup.moshi.Json

data class Weight(
    @Json(name = "imperial") var imperial: String?,
    @Json(name = "metric") var metric: String?
)
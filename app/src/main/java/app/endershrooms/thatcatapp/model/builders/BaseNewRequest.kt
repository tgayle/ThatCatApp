package app.endershrooms.thatcatapp.model.builders

import com.squareup.moshi.Json

abstract class BaseNewRequest(
        @field:Json(name = "image_id")
        var imageId: String,

        @field:Json(name = "sub_id")
        var subId: String?
) {

    constructor(imageId: String): this(imageId, null)
}

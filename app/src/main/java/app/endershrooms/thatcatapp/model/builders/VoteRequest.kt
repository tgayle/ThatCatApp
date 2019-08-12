package app.endershrooms.thatcatapp.model.builders

import com.squareup.moshi.Json

class VoteRequest(
        imageId: String,
        subId: String,
        @field:Json(name = "value") var value: Int) : BaseNewRequest(imageId, subId) {

    companion object {
        const val VOTE_DISLIKE = 0
        const val VOTE_LIKE = 1

        @JvmStatic
        fun fromBoolean(liked: Boolean): Int {
            return if (liked) VOTE_LIKE else VOTE_DISLIKE
        }
    }
}

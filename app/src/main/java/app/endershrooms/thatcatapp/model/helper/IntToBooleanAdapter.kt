package app.endershrooms.thatcatapp.model.helper

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class IntToBooleanAdapter {
    @FromJson
    @IntToBoolean
    fun fromJson(value: Int): Boolean = value != 0

    @ToJson
    fun toJson(@IntToBoolean value: Boolean): Int = if (value) 1 else 0
}
package app.endershrooms.thatcatapp.db

import androidx.room.TypeConverter
import app.endershrooms.thatcatapp.model.Weight

class Converters {

    @TypeConverter
    fun weightFromString(value: String): Weight {
        val fields = value.split("\\|".toRegex())
        return if (fields.size != 2) {
            Weight("n/a", "n/a")
        } else {
            Weight(fields[0], fields[1])
        }
    }

    @TypeConverter
    fun weightToDatabaseString(weight: Weight): String ="${weight.imperial}|${weight.metric}"

    @TypeConverter
    fun breedIdsToDatabaseString(breedIds: List<String>?): String {
        return breedIds?.joinToString("|") ?: ""
    }

    @TypeConverter
    fun breedIdsFromString(databaseIdString: String): List<String> {
        val ids = databaseIdString.trim().split("\\|".toRegex())
        return ids.toList()
    }

}

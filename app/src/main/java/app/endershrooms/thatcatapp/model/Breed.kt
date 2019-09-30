package app.endershrooms.thatcatapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.endershrooms.thatcatapp.model.helper.IntToBoolean
import com.squareup.moshi.Json

@Entity
data class Breed(
    @Json(name = "weight")
    var weight: Weight?,

    @NonNull
    @PrimaryKey
    @field:Json(name = "id")
    var breedId: String,

    @Json(name = "name")
    var name: String?,
    @Json(name = "cfa_url")
    var cfaUrl: String?,
    @Json(name = "vetstreet_url")
    var vetstreetUrl: String?,
    @Json(name = "vcahospitals_url")
    var vcahospitalsUrl: String?,
    @Json(name = "temperament")
    var temperament: String?,
    @Json(name = "origin")
    var origin: String?,
    @Json(name = "country_codes")
    var countryCodes: String?,
    @Json(name = "country_code")
    var countryCode: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "life_span")
    var lifeSpan: String?,
    @Json(name = "indoor")
    var indoor: Int?,
    @Json(name = "lap")
    var lap: Int?,
    @Json(name = "alt_names")
    var altNames: String?,
    @Json(name = "adaptability")
    var adaptability: Int?,
    @Json(name = "affection_level")
    var affectionLevel: Int?,
    @Json(name = "child_friendly")
    var childFriendly: Int?,
    @Json(name = "dog_friendly")
    var dogFriendly: Int?,
    @Json(name = "energy_level")
    var energyLevel: Int?,
    @Json(name = "grooming")
    var grooming: Int?,
    @Json(name = "health_issues")
    var healthIssues: Int?,
    @Json(name = "intelligence")
    var intelligence: Int?,
    @Json(name = "shedding_level")
    var sheddingLevel: Int?,
    @Json(name = "social_needs")
    var socialNeeds: Int?,
    @Json(name = "stranger_friendly")
    var strangerFriendly: Int?,
    @Json(name = "vocalisation")
    var vocalisation: Int?,

    @Json(name = "experimental")
    @field:IntToBoolean
    var experimental: Boolean = false,

    @field:Json(name = "hairless")
    @field:IntToBoolean
    var hairless: Boolean = false,

    @field:Json(name = "natural")
    @field:IntToBoolean
    var natural: Boolean = false,

    @field:Json(name = "rare")
    @field:IntToBoolean
    var rare: Boolean = false,

    @field:Json(name = "rex")
    @field:IntToBoolean
    var rex: Boolean = false,

    @field:Json(name = "suppressed_tail")
    @field:IntToBoolean
    var suppressedTail: Boolean = false,

    @field:Json(name = "short_legs")
    @field:IntToBoolean
    var shortLegs: Boolean = false,

    @Json(name = "wikipedia_url")
    var wikipediaUrl: String?,

    //TODO: Why does every variable with more than one annotation require @field?
    @field:Json(name = "hypoallergenic")
    @field:IntToBoolean
    var hypoallergenic: Boolean = false
)
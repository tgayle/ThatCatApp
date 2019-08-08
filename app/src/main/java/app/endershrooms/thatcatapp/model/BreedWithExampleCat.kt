package app.endershrooms.thatcatapp.model

import androidx.room.Embedded

class BreedWithExampleCat(@Embedded var breed: Breed?,
                          @Embedded var cat: Cat?)

package app.endershrooms.thatcatapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import app.endershrooms.thatcatapp.db.dao.*
import app.endershrooms.thatcatapp.model.*

@Database(version = 4, entities = [Category::class, Breed::class, Vote::class, Favorite::class, Cat::class])
@TypeConverters(Converters::class)
abstract class CatDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun voteDao(): VoteDao

    abstract fun breedDao(): BreedDao

    abstract fun favoriteDao(): FavoriteDao

    abstract fun catDao(): CatDao

    companion object {

        @JvmField
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Cat` (`id` TEXT NOT NULL, `breedIds` TEXT, `url` TEXT, `width` INTEGER, `height` INTEGER, PRIMARY KEY (`id`))")
            }
        }

        @JvmField
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `Cat` RENAME TO _Cat_old")
                database.execSQL("CREATE TABLE `Cat` (`catId` TEXT NOT NULL, `breedIds` TEXT, `url` TEXT, `width` INTEGER, `height` INTEGER, PRIMARY KEY (`catId`))")
                database.execSQL("INSERT INTO `Cat` SELECT * FROM _Cat_old")
                database.execSQL("DROP TABLE _Cat_old")

                database.execSQL("ALTER TABLE `Breed` RENAME TO _Breed_old")
                database.execSQL("CREATE TABLE `Breed` (`weight` TEXT, `breedId` TEXT NOT NULL, `name` TEXT, `cfaUrl` TEXT, `vetstreetUrl` TEXT, `vcahospitalsUrl` TEXT, `temperament` TEXT, `origin` TEXT, `countryCodes` TEXT, `countryCode` TEXT, `description` TEXT, `lifeSpan` TEXT, `indoor` INTEGER, `lap` INTEGER, `altNames` TEXT, `adaptability` INTEGER, `affectionLevel` INTEGER, `childFriendly` INTEGER, `dogFriendly` INTEGER, `energyLevel` INTEGER, `grooming` INTEGER, `healthIssues` INTEGER, `intelligence` INTEGER, `sheddingLevel` INTEGER, `socialNeeds` INTEGER, `strangerFriendly` INTEGER, `vocalisation` INTEGER, `experimental` INTEGER NOT NULL, `hairless` INTEGER NOT NULL, `natural` INTEGER NOT NULL, `rare` INTEGER NOT NULL, `rex` INTEGER NOT NULL, `suppressedTail` INTEGER NOT NULL, `shortLegs` INTEGER NOT NULL, `wikipediaUrl` TEXT, `hypoallergenic` INTEGER NOT NULL, PRIMARY KEY(`breedId`))")
                database.execSQL("INSERT INTO `Breed` SELECT * FROM _Breed_old")
                database.execSQL("DROP TABLE _Breed_old")
            }
        }
    }
}

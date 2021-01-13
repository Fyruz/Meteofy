package truebeans.fyruz.meteofy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace


@Database(entities = arrayOf(WeatherPlace::class), version = 2, exportSchema = false)
abstract class MeteofyDatabase : RoomDatabase() { // : is the extend

    abstract fun weatherPlaceDAO(): WeatherPlaceDAO

    companion object {

        @Volatile
        private var INSTANCE: MeteofyDatabase? = null

        fun getDatabase(context: Context): MeteofyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MeteofyDatabase::class.java,
                        "meteofy_database"
                )
                        /*.addMigrations(object : Migration(1, 2) {
                            override fun migrate(database: SupportSQLiteDatabase) {
                                database.execSQL("ALTER TABLE WeatherPlaces " +
                                        "DROP placeId")
                                database.execSQL("ALTER TABLE WeatherPlaces" +
                                        "ADD PRIMARY KEY(placeName)"
                                )
                            }
                        })*/
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
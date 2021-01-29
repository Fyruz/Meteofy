package truebeans.fyruz.meteofy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace


@Database(entities = [WeatherPlace::class], version = 2, exportSchema = false)
abstract class MeteofyDatabase : RoomDatabase() {

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
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
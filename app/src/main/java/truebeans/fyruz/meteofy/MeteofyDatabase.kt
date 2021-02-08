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

        private const val DATABASE_NAME: String = "meteofy_database"
        const val TABLE_NAME = "WeatherPlaces"
        var TEST_MODE = false

        private var INSTANCE: MeteofyDatabase? = null
        private var DAOINSTANCE: WeatherPlaceDAO? = null

        fun getDatabaseInstance(context: Context) : MeteofyDatabase {
            if(INSTANCE == null) {
                if (TEST_MODE) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context, MeteofyDatabase::class.java)
                            .allowMainThreadQueries()
                            .build()
                    DAOINSTANCE = INSTANCE?.weatherPlaceDAO()
                } else {
                    INSTANCE = Room.databaseBuilder(context, MeteofyDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build()
                    DAOINSTANCE = INSTANCE?.weatherPlaceDAO()
                }
            }
            return INSTANCE!!
        }
    }

}
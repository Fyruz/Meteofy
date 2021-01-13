package truebeans.fyruz.meteofy.DataAccessObject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import truebeans.fyruz.meteofy.Models.WeatherPlace

@Dao
interface WeatherPlaceDAO {

    @Query("SELECT * FROM WeatherPlaces" )
    fun getWeatherPlaces(): Flow<List<WeatherPlace>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewPlace(newPlace: WeatherPlace)
}
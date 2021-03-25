package truebeans.fyruz.meteofy.Models.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace

@Dao
interface WeatherPlaceDAO {

    @Query("SELECT * FROM WeatherPlaces")
    fun getWeatherPlacesAsLiveData(): LiveData<List<WeatherPlace>>

    @Query("SELECT count(*) FROM WeatherPlaces")
    fun getPlacesCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPlace(newPlace: WeatherPlace)

    @Query("DELETE FROM WeatherPlaces WHERE placeName = :currentPlaceName")
    fun deleteByPlaceId(currentPlaceName: String)

}
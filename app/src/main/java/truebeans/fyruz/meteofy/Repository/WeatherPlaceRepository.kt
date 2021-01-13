package truebeans.fyruz.meteofy.Repository

import kotlinx.coroutines.flow.Flow
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace

class WeatherPlaceRepository(private val weatherPlaceDAO: WeatherPlaceDAO) {

    val allPlaces: Flow<List<WeatherPlace>> = weatherPlaceDAO.getWeatherPlaces()

    suspend fun insert(weatherPlace: WeatherPlace) {
        weatherPlaceDAO.insertNewPlace(weatherPlace)
    }

    companion object{
        @Volatile private var instance: WeatherPlaceRepository? = null

        fun getInstance(wPlaceDAO: WeatherPlaceDAO) =
                instance ?: synchronized(this){
                    instance ?: WeatherPlaceRepository(wPlaceDAO).also { instance = it }
                }

    }

}
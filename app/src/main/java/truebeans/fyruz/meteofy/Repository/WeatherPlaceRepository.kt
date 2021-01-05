package truebeans.fyruz.meteofy.Repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace

class WeatherPlaceRepository(private val weatherPlaceDAO: WeatherPlaceDAO) {

    val allPlaces: Flow<List<WeatherPlace>> = weatherPlaceDAO.getWeatherPlaces()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weatherPlace: WeatherPlace) {
        weatherPlaceDAO.insertNewPlace(weatherPlace)
    }

}
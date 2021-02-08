package truebeans.fyruz.meteofy.Repository

import androidx.lifecycle.LiveData
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace

class WeatherPlaceRepository(private val weatherPlaceDAO: WeatherPlaceDAO) {

    val places: LiveData<List<WeatherPlace>> = weatherPlaceDAO.getWeatherPlacesAsLiveData()

    fun insert(weatherPlace: WeatherPlace) {
        weatherPlaceDAO.insertNewPlace(weatherPlace)
    }

    fun delete(placeId: String){
        weatherPlaceDAO.deleteByPlaceId(placeId)
    }

    companion object{
        @Volatile private var instance: WeatherPlaceRepository? = null

        fun getRepositoryInstance(wPlaceDAO: WeatherPlaceDAO) =
                instance ?: synchronized(this){
                    instance ?: WeatherPlaceRepository(wPlaceDAO).also { instance = it }
                }

    }

}
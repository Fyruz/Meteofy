package truebeans.fyruz.meteofy.Models.Repository

import androidx.lifecycle.LiveData
import truebeans.fyruz.meteofy.Models.Local.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace

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
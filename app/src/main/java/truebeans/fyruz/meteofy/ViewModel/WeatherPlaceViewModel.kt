package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import truebeans.fyruz.meteofy.Models.WeatherPlace

class WeatherPlaceViewModel {
    private val placeMutableList = mutableListOf<WeatherPlace>()
    private val places = MutableLiveData<List<WeatherPlace>>()

    init{
        places.value = placeMutableList
    }

    fun addWeatherPlace(weatherPlace: WeatherPlace){

        placeMutableList.add(weatherPlace)
        places.value = placeMutableList

    }

    fun getWeatherPlaces() = places as LiveData<List<WeatherPlace>>  //remove the mutable attribute

}
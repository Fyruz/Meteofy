package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import truebeans.fyruz.meteofy.Models.Repository.WeatherPlaceRepository

class WeatherPlaceViewModel(private val repo: WeatherPlaceRepository) : ViewModel() {

    //binding
    val places: LiveData<List<WeatherPlace>> = repo.places


    //Insert new element inside the DB
    fun insertPlace(place: WeatherPlace) = viewModelScope.launch {
        repo.insertPlace(place)
    }

    //Delete an element from DB
    fun deletePlace(placeId: String) = viewModelScope.launch {
        repo.deletePlace(placeId)
    }

}
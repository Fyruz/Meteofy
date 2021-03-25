package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import truebeans.fyruz.meteofy.Models.Repository.WeatherPlaceRepository

class WeatherPlaceViewModel(private val repo: WeatherPlaceRepository) : ViewModel() {

    //binding
    val places: LiveData<List<WeatherPlace>> = repo.places

    //Insert new element inside the DB
    fun insert(place: WeatherPlace) = viewModelScope.launch {
        repo.insert(place)
    }

    //Delete an element from DB
    fun delete(placeId: String) = viewModelScope.launch {
        repo.delete(placeId)
    }

}
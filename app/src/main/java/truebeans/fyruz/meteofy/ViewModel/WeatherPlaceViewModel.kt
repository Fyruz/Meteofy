package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository

class WeatherPlaceViewModel(private val repo: WeatherPlaceRepository) : ViewModel() {

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
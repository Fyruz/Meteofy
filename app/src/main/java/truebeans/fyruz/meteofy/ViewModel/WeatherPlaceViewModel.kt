package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository

class WeatherPlaceViewModel(private val repo: WeatherPlaceRepository) : ViewModel() {

    val places: LiveData<List<WeatherPlace>> = repo.allPlaces.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: WeatherPlace) = viewModelScope.launch {
        repo.insert(word)
    }
}
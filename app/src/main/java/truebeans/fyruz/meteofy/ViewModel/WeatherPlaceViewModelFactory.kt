package truebeans.fyruz.meteofy.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository

class WeatherPlaceViewModelFactory(private val repo: WeatherPlaceRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return WeatherPlaceViewModel(repo) as T
    }
}
package truebeans.fyruz.meteofy

import android.app.Application
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository

class MeteoFyApplication : Application() {
    val database by lazy { MeteofyDatabase.getDatabase(this) }
    val repository by lazy { WeatherPlaceRepository(database.weatherPlaceDAO()) }
}
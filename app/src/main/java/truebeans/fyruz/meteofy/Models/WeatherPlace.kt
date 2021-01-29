package truebeans.fyruz.meteofy.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherPlaces")
data class WeatherPlace (@PrimaryKey val placeName: String,
                         val placeTemp: String,
                         val placeWeather: String)

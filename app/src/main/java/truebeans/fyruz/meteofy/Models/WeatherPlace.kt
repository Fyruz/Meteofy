package truebeans.fyruz.meteofy.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherPlaces")
data class WeatherPlace (@PrimaryKey(autoGenerate = true) val placeId: Int, val placeName: String, val placeTemp: Int, val placeWeather: String){

    override fun toString(): String {
        return "$placeId - $placeName - $placeTemp - $placeWeather"
    }
}
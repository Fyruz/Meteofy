package truebeans.fyruz.meteofy.Models.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import truebeans.fyruz.meteofy.MeteofyDatabase

@Entity(tableName = MeteofyDatabase.TABLE_NAME)
data class WeatherPlace (@PrimaryKey var placeName: String,
                         val placeTemp: String,
                         val placeWeather: String)

package truebeans.fyruz.meteofy.Utils

import OpenWeatherMapJsonResponse
import android.app.Activity
import android.widget.Toast
import com.google.gson.Gson
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel
import kotlin.math.roundToInt

class OpenWeatherMapCaller(private val placeName: String,
                           private val weatherPlaceViewModel: WeatherPlaceViewModel,
                           private val context: Activity) : InternetCaller() {

    //OWM private API key
    private val apiKey : String = "70c4605ff86d3fba67646a0c752a7d85"

    init {
        startConnection()
    }

    override fun getPageUrl(): String {
        return "/data/2.5/"
    }

    override fun getHostingUrl(): String {
        return "http://api.openweathermap.org"
    }
    override fun onErrorReceived(error: String) {
        context.runOnUiThread {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getParameters(): String {
        return "weather?q=$placeName&appid=$apiKey&units=metric"
    }

    override fun onResponseReceived(response: String) {

        if(response.contains("404")){
            onErrorReceived("Citta non presente nel DB")
        }else {
            val rawPlace = Gson().fromJson(response, OpenWeatherMapJsonResponse::class.java)
            val placeTemp = rawPlace.main.feels_like.roundToInt()
            val placeName = rawPlace.name
            val weatherType = rawPlace.weather[0].main
            weatherPlaceViewModel.insert(
                WeatherPlace(placeName, "$placeTemp°", weatherType)
            )
        }
    }
}
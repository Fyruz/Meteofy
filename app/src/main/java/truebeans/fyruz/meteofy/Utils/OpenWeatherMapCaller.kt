package truebeans.fyruz.meteofy.Utils

import OpenWeatherMapJsonResponse
import com.google.gson.Gson
import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel

class OpenWeatherMapCaller(val placeName: String, val weatherPlaceViewModel: WeatherPlaceViewModel) : InternetCaller() {

    private val apiKey : String = "70c4605ff86d3fba67646a0c752a7d85"

    init {
        internetCall()
    }

    override fun getPageUrl(): String {
        return "/data/2.5/"
    }

    override fun getHostingUrl(): String {
        return "http://api.openweathermap.org"
    }

    override fun responseReceived(response: String) {
        val x  = Gson().fromJson(response, OpenWeatherMapJsonResponse::class.java)

        //TODO create a WP and pass to wpviewmodel
    }

    override fun getParameters(): String {
        return "weather?q=$placeName&appid=$apiKey&units=metric"
    }
}
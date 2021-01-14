package truebeans.fyruz.meteofy.Utils

import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel

class OpenWeatherMapCaller(val placeName: String, val weatherPlaceViewModel: WeatherPlaceViewModel) : InternetCaller() {

    val apiKey = "70c4605ff86d3fba67646a0c752a7d85"

    override fun getPageUrl(): String {
        return "/data/2.5/"
    }

    override fun getHostingUrl(): String {
        return "http://api.openweathermap.org"
    }

    override fun responseReceived(response: String) {
        //TODO parse JSON to class(?) and insert response in wpViewModel
    }

    override fun getParameters(): String {
        return "weather?q=$placeName&appid=$apiKey&units=metric"
    }
}
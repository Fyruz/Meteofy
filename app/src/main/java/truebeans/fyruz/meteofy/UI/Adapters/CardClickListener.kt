package truebeans.fyruz.meteofy.UI.Adapters

import truebeans.fyruz.meteofy.Models.Data.WeatherPlace

interface CardClickListener {
    fun onCardClickListener(data: WeatherPlace)
    fun onCardLongClickListener(data: WeatherPlace)
}
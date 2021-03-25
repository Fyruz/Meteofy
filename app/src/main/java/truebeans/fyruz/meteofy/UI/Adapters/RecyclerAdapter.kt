package truebeans.fyruz.meteofy.UI.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import truebeans.fyruz.meteofy.R

class RecyclerAdapter(private val context: Context,
                      private var weatherPlaceList: MutableList<WeatherPlace>,
                      private val cellClickListener: CardClickListener) : RecyclerView.Adapter<RecyclerAdapter.WeatherPlaceHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherPlaceHolder{
        return WeatherPlaceHolder(
                LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: WeatherPlaceHolder, position: Int) {
        val currentPlace = weatherPlaceList[position]

        populateViewHolder(holder, currentPlace)

        holder.cardView.setOnClickListener {
            cellClickListener.onCardClickListener(currentPlace)
        }
        holder.cardView.setOnLongClickListener {
            cellClickListener.onCardLongClickListener(currentPlace)
            true
        }

    }

    private fun populateViewHolder(holder: WeatherPlaceHolder, place : WeatherPlace){
        holder.placeName.text = place.placeName
        holder.placeTemp.text = place.placeTemp
        holder.weatherImage.setImageDrawable(getWeatherImage(place.placeWeather))
        holder.cardView.setCardBackgroundColor(getCardBackground(place.placeWeather))
    }

    private fun getCardBackground(weatherType: String): Int{
        return when(weatherType) {
            "Rain" -> Color.parseColor("#ff5722")
            "Clear" -> Color.parseColor("#0288d1")
            "Snow" -> Color.parseColor("#ff5722")
            "Clouds" -> Color.parseColor("#fbc02d")
            "Fog" -> Color.parseColor("#fbc02d")
            else -> Color.parseColor("#8bc34a")
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getWeatherImage(weatherType: String): Drawable?{
        return when(weatherType){
            "Rain" -> context.getDrawable(R.drawable.icon_rain)
            "Clear" -> context.getDrawable(R.drawable.icon_sun)
            "Snow" -> context.getDrawable(R.drawable.icon_snow)
            "Clouds" -> context.getDrawable(R.drawable.icon_cloud)
            "Fog" -> context.getDrawable(R.drawable.icon_cloud)
            else -> context.getDrawable(R.drawable.icon_default)
        }
    }

    fun itemsHasChanged(newPlaces: List<WeatherPlace>){
        weatherPlaceList.clear()
        weatherPlaceList.addAll(newPlaces)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weatherPlaceList.size
    }

    class WeatherPlaceHolder(v: View) : RecyclerView.ViewHolder(v){
        var placeName : TextView = v.findViewById(R.id.city_name)
        var placeTemp : TextView = v.findViewById(R.id.city_temp)
        var weatherImage : ImageView = v.findViewById(R.id.weather_type)
        var cardView: CardView = v.findViewById(R.id.cardView)
    }

}

package truebeans.fyruz.meteofy.Adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.R

class RecyclerAdapter(private val context: Context, private var weatherPlaceList: MutableList<WeatherPlace>) : RecyclerView.Adapter<RecyclerAdapter.WeatherPlaceHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.WeatherPlaceHolder{
        val view : View = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return WeatherPlaceHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.WeatherPlaceHolder,
                                  position: Int) {
        val currentPlace = weatherPlaceList.get(position)
        holder.placeName.text = currentPlace.placeName
        holder.placeTemp.text = currentPlace.placeTemp
        //holder.weatherImage.setImageDrawable(getWeatherImage(currentPlace.placeWeather))

    }

    fun itemsHasChanged(newPlaces : List<WeatherPlace>){
        weatherPlaceList.clear()
        weatherPlaceList.addAll(newPlaces)
        notifyDataSetChanged()
    }

    private fun getWeatherImage(weatherType: String) : Drawable{
        TODO()
    }

    override fun getItemCount(): Int {
        return weatherPlaceList.size
    }

    class WeatherPlaceHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        var placeName : TextView
        var placeTemp : TextView
        var weatherImage : ImageView
        var cardView: CardView


        //3
        init {
            //init delle views
            v.setOnClickListener(this)
            placeName = v.findViewById(R.id.city_name)
            placeTemp = v.findViewById(R.id.city_temp)
            weatherImage = v.findViewById(R.id.weather_type)
            cardView = v.findViewById(R.id.cardView)
        }

        //4
        override fun onClick(v: View) {
            Log.d("Click Salvato", "CLICK!")
        }
    }

}

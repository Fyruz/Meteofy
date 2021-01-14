package truebeans.fyruz.meteofy.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import truebeans.fyruz.meteofy.Adapters.RecyclerAdapter
import truebeans.fyruz.meteofy.MeteofyDatabase
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.R
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository
import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : RecyclerAdapter
    private lateinit var weatherPlaceViewModel : WeatherPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        initViewModel()
    }

    private fun initUI(){
        initRecyclerView()
        initFAB()
    }

    private fun initRecyclerView(){
        val mRecycler : RecyclerView = findViewById(R.id.main_recycler)
        mRecycler.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(this, ArrayList())
        mRecycler.adapter = adapter
    }

    private fun initFAB(){
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            weatherPlaceViewModel.insert(WeatherPlace("Milano","10", "Ello"))
            //TODO CALL TO OWM, create dialog to ask the place
        }
    }

    private fun initViewModel(){
        weatherPlaceViewModel = WeatherPlaceViewModel(WeatherPlaceRepository
                .getInstance(MeteofyDatabase
                        .getDatabase(this)
                        .weatherPlaceDAO()))

        weatherPlaceViewModel
                .places
                .observe(this, { places -> adapter.itemsHasChanged(places) })
    }
}
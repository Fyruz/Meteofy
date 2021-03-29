package truebeans.fyruz.meteofy.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText
import truebeans.fyruz.meteofy.UI.Adapters.CardClickListener
import truebeans.fyruz.meteofy.UI.Adapters.RecyclerAdapter
import truebeans.fyruz.meteofy.MeteofyDatabase
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import truebeans.fyruz.meteofy.R
import truebeans.fyruz.meteofy.Models.Repository.WeatherPlaceRepository
import truebeans.fyruz.meteofy.Utils.OpenWeatherMapCaller
import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel

class MainActivity : AppCompatActivity() , CardClickListener{

    private lateinit var mainAdapter: RecyclerAdapter
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

    //Initialize recycler view and its component
    private fun initRecyclerView() {
        val mRecycler: RecyclerView = findViewById(R.id.main_recycler)
        mainAdapter = RecyclerAdapter(this, ArrayList(), this)
        mRecycler.layoutManager = LinearLayoutManager(this)
        mRecycler.adapter = mainAdapter
    }

    //Initialize the floating action button
    private fun initFAB(){
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            run {
                openSheets()
            }
        }
    }

    //Initialize the ViewModel
    private fun initViewModel(){
        weatherPlaceViewModel = WeatherPlaceViewModel(WeatherPlaceRepository
                .getRepositoryInstance(MeteofyDatabase
                        .getDatabaseInstance(this)
                        .weatherPlaceDAO()))
        weatherPlaceViewModel
                .places
                .observe(this, { places -> mainAdapter.itemsHasChanged(places) })
    }

    //Manage the input method for places
    private fun openSheets(){
       InputSheet().show(this){
            title("Aggiungi Cittá")
            with(InputEditText {
                required(true)
                label("Inserisci nuova cittá")
            })
            onPositive { result -> result.getString("0")
                    ?.let { OpenWeatherMapCaller(it, weatherPlaceViewModel, this@MainActivity) }
            }
        }
    }

    //Manage the click on a RecyclerView cell
    override fun onCardClickListener(data: WeatherPlace) {
        OpenWeatherMapCaller(data.placeName, weatherPlaceViewModel, this@MainActivity)
    }

    //Manage the long click on a RecyclerView cell
    override fun onCardLongClickListener(data: WeatherPlace) {
        weatherPlaceViewModel.deletePlace(data.placeName)
    }
}
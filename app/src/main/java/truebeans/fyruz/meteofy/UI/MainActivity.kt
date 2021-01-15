package truebeans.fyruz.meteofy.UI

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText
import truebeans.fyruz.meteofy.Adapters.RecyclerAdapter
import truebeans.fyruz.meteofy.MeteofyDatabase
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.R
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository
import truebeans.fyruz.meteofy.Utils.OpenWeatherMapCaller
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
            run {
                Log.i("fab", "dentro fab")
                openSheets()
            }
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

    private fun openSheets(){
       InputSheet().show(this){
            title("Aggiungi Cittá")
            with(InputEditText{
                required(true)
                label("Inserisci nuova cittá")
                hint("Firenze, ...")
            })
            onPositive {
                result -> OpenWeatherMapCaller(result.getString("0").toString(), weatherPlaceViewModel)
            }
            onNegative { showToast("InputSheet cancelled", "No result") }
        }
    }

    private fun showToast(s: String, s1: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}
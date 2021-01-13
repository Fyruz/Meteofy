package truebeans.fyruz.meteofy.UI

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import truebeans.fyruz.meteofy.Adapters.RecyclerAdapter
import truebeans.fyruz.meteofy.MeteofyDatabase
import truebeans.fyruz.meteofy.Models.WeatherPlace
import truebeans.fyruz.meteofy.R
import truebeans.fyruz.meteofy.Repository.WeatherPlaceRepository
import truebeans.fyruz.meteofy.ViewModel.WeatherPlaceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weatherPlaceRepository = WeatherPlaceRepository.getInstance(MeteofyDatabase.getDatabase(this).weatherPlaceDAO())
        val weatherPlaceViewModel = WeatherPlaceViewModel(weatherPlaceRepository);
        initRecyclerView()
        //val list : List<WeatherPlace> = weatherPlaceViewModel.places.value!!
        weatherPlaceViewModel
                .places
                .observe(this, { places -> adapter.itemsHasChanged(places) })
        //FAB
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            weatherPlaceViewModel.insert(WeatherPlace("Milano","10", "Ello"))
        }
    }

    fun initRecyclerView(){
        val mRecycler : RecyclerView = findViewById(R.id.main_recycler)
        mRecycler.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(this, ArrayList())
        mRecycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
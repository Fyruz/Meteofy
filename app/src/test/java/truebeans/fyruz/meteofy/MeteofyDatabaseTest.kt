package truebeans.fyruz.meteofy

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.jraska.livedata.test
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import truebeans.fyruz.meteofy.DataAccessObject.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.WeatherPlace
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class MeteofyDatabaseTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var dao: WeatherPlaceDAO
    private lateinit var appContext : Context
    private lateinit var db : MeteofyDatabase

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        MeteofyDatabase.TEST_MODE = true
        db = MeteofyDatabase.getDatabaseInstance(appContext)
        dao = db.weatherPlaceDAO()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndRetrieveData() {
        val weatherPlace = WeatherPlace("test_place", "10", "Clouds")
        val testObserver = dao.getWeatherPlacesAsLiveData().test()
        dao.insertNewPlace(weatherPlace)
        Assert.assertTrue(testObserver.value()[0].placeName == weatherPlace.placeName)
    }

    @Test
    fun insertAndDeleteData(){
        val weatherPlace = WeatherPlace("test_place", "10", "Clouds")
        val testObserver = dao.getWeatherPlacesAsLiveData().test()
        dao.insertNewPlace(weatherPlace)
        dao.deleteByPlaceId(weatherPlace.placeName)
        Assert.assertTrue(testObserver.value().isEmpty())
    }
}

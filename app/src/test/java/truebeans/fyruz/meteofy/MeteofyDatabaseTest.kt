package truebeans.fyruz.meteofy

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import truebeans.fyruz.meteofy.Models.Local.WeatherPlaceDAO
import truebeans.fyruz.meteofy.Models.Data.WeatherPlace
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class MeteofyDatabaseTest {

    private val testData = WeatherPlace("test_place", "10", "Clouds")
    private val appContext : Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var db : MeteofyDatabase
    private lateinit var dao: WeatherPlaceDAO
    private lateinit var testObserver : TestObserver<List<WeatherPlace>>

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(appContext, MeteofyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        dao = db.weatherPlaceDAO()
        testObserver= dao.getWeatherPlacesAsLiveData().test()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndDeleteData(){
        insertData(testData)
        dao.deleteByPlaceId(testData.placeName)
        Assert.assertTrue(testObserver.value().isEmpty())
    }

    @Test
    fun insertAndRetrieveData() {
        insertData(testData)
        Assert.assertTrue(testObserver.value()[0].placeName == testData.placeName)
    }

    private fun insertData(data: WeatherPlace){
        dao.insertNewPlace(data)
    }

}

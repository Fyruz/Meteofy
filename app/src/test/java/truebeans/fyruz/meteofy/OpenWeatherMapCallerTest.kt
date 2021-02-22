package truebeans.fyruz.meteofy

import OpenWeatherMapJsonResponse
import android.os.Build
import com.google.gson.Gson
import okhttp3.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import truebeans.fyruz.meteofy.Utils.InternetCaller
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class OpenWeatherMapCallerTest : InternetCaller(){

    private val testPlaceName: String = "Firenze"
    private val testPlaceNameWithError: String = "RandomPlaceThatNotExist"
    private val apiKey: String = "70c4605ff86d3fba67646a0c752a7d85"
    private val urlTest: String = "http://api.openweathermap.org/data/2.5/weather?q=$testPlaceName&appid=$apiKey&units=metric"

    @Test //Testing a normal request to OWM
    override fun startConnection() {
        val testRequest = createRequest()
        Assert.assertEquals(testRequest.url().toString(), urlTest)

        val call = OkHttpClient().newCall(testRequest)
        Assert.assertNotNull(call)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onErrorReceived("Errore nel collegamento al DB")
            }

            override fun onResponse(call: Call, response: Response) {
                Assert.assertNotNull(response)
                response.body()?.string()?.let {
                    Assert.assertNotNull(it)
                    onResponseReceived(it)
                }
            }
        })
    }

    @Test //Testing a request to OWM with an error
    fun startConnectionWithError() {
        val call = OkHttpClient().newCall(Request.Builder()
            .url(getHostingUrl() + getPageUrl() + getParametersWithError())
            .build())
        Assert.assertNotNull(call)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onErrorReceived("Errore nel collegamento al DB")
            }

            override fun onResponse(call: Call, response: Response) {
                Assert.assertNotNull(response)
                response.body()?.string()?.let {
                    Assert.assertNotNull(it)
                    Assert.assertTrue(it.contains("404"))
                    onResponseReceived(it)
                }
            }
        })
    }

    private fun getParametersWithError(): String {
        return "weather?q=$testPlaceNameWithError&appid=$apiKey&units=metric"
    }

    private fun createRequest(): Request {
        return Request.Builder()
            .url(getHostingUrl() + getPageUrl() + getParameters())
            .build()
    }

    override fun getParameters(): String {
        return "weather?q=$testPlaceName&appid=$apiKey&units=metric"
    }

    override fun getPageUrl(): String {
        return "/data/2.5/"
    }

    override fun getHostingUrl(): String {
        return "http://api.openweathermap.org"
    }

    override fun onResponseReceived(response: String) {
        if (response.contains("404")) {
            onErrorReceived("Citta non presente nel DB")
            return
        }

        val rawPlace = Gson().fromJson(response, OpenWeatherMapJsonResponse::class.java)
        Assert.assertNotNull(rawPlace)

        val receivedPlaceName = rawPlace.name
        Assert.assertEquals(receivedPlaceName, "Florence")
    }

    override fun onErrorReceived(error: String) {
        Assert.assertNotNull(error)
    }

}

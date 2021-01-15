package truebeans.fyruz.meteofy.Utils

import android.util.Log
import okhttp3.*
import java.io.IOException

abstract class InternetCaller : InternetCall{

    override fun internetCall() {
        val client = OkHttpClient()
        val request = createRequest()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO()
            }

            override fun onResponse(call: Call, response: Response) {

                responseReceived(response.body()?.string().toString())
            }
        })
    }

    private fun createRequest() : Request{
        val url = getHostingUrl() + getPageUrl() + getParameters()
        return Request.Builder()
            .url(getHostingUrl() + getPageUrl() + getParameters())
            .build()
    }

    abstract fun getParameters() : String

    abstract fun getPageUrl() : String

    abstract fun getHostingUrl() : String

    abstract override fun responseReceived(response: String)
}
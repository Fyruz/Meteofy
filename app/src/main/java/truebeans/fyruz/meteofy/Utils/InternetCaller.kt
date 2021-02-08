package truebeans.fyruz.meteofy.Utils

import okhttp3.*
import java.io.IOException

abstract class InternetCaller : InternetConnection{

    override fun startConnection() {
        OkHttpClient()
                .newCall(createRequest())
                .enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                            onErrorReceived("Errore nel collegamento al servizio internet")
                    }

                    override fun onResponse(call: Call, response: Response) {
                response.body()?.string()?.let {
                    onResponseReceived(it)
                }
            }
        })
    }

    private fun createRequest() : Request{
        return Request.Builder()
            .url(getHostingUrl() + getPageUrl() + getParameters())
            .build()
    }

    abstract fun getParameters() : String

    abstract fun getPageUrl() : String

    abstract fun getHostingUrl() : String
}



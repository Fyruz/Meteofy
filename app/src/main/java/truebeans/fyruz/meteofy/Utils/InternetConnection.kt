package truebeans.fyruz.meteofy.Utils

interface InternetConnection {
    fun startConnection()
    fun onResponseReceived(response : String)
    fun onErrorReceived(error: String)
}
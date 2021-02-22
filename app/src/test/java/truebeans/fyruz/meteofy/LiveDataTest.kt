package truebeans.fyruz.meteofy

import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.jraska.livedata.test
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class LiveDataTest {

    @Test
    fun testLiveDataObserver() {
        val mutableLiveData = MutableLiveData<Int>()
        val testObserver = mutableLiveData.test()

        Assert.assertTrue(mutableLiveData.hasObservers())
        testObserver.assertNoValue()

        mutableLiveData.value = 5

        Assert.assertTrue(testObserver.valueHistory().contains(5))
        testObserver.assertValue(5)

        mutableLiveData.removeObserver(testObserver)
        Assert.assertTrue(!mutableLiveData.hasObservers())
    }

}
package truebeans.fyruz.meteofy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jraska.livedata.test
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class LiveDataTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

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
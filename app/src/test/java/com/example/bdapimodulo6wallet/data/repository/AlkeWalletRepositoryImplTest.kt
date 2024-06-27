package com.example.bdapimodulo6wallet.data.repository
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bdapimodulo6wallet.data.model.LoginRequest
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import com.example.bdapimodulo6wallet.data.response.LoginResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class AlkeWalletRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context
    private lateinit var repository: AlkeWalletRepositoryImpl
    private val apiService = Mockito.mock(WalletApiService::class.java)

    @Before
    fun setup() {
        // Usa un contexto simulado en lugar de ApplicationProvider.getApplicationContext()
        context = Mockito.mock(Context::class.java)
        repository = AlkeWalletRepositoryImpl(context).apply {
            apiService = this@AlkeWalletRepositoryImplTest.apiService
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLogin() = runBlockingTest {
        val expectedToken = "mockToken"
        val loginRequest = LoginRequest(email = "1", password = "1")
        val loginResponse = LoginResponse(accessToken = expectedToken)

        `when`(apiService.login(loginRequest)).thenReturn(loginResponse)

        val result = repository.login("1", "1")

        Assert.assertEquals(expectedToken, result)
    }

}
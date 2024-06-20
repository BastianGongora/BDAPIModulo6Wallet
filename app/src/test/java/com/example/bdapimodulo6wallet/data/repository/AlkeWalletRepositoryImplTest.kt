package com.example.bdapimodulo6wallet.data.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bdapimodulo6wallet.data.model.LoginRequest
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import com.example.bdapimodulo6wallet.data.response.LoginResponse

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class AlkeWalletRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AlkeWalletRepositoryImpl
    private val apiService = Mockito.mock(WalletApiService::class.java)

    @Before
    fun setup() {
        repository = AlkeWalletRepositoryImpl(ApplicationProvider.getApplicationContext())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLogin() = runBlockingTest {
        val email = "1"
        val password = "1"
        val accessToken = "test_token"

        val loginResponse = LoginResponse(accessToken)
        `when`(apiService.login(LoginRequest(email, password))).thenReturn(loginResponse)

        val result = repository.login(email, password)

        Assert.assertEquals(accessToken, result)
        Mockito.verify(apiService).login(LoginRequest(email, password))
    }
}
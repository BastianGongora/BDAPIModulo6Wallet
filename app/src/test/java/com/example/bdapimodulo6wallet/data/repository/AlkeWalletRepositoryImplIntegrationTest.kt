package com.example.bdapimodulo6wallet.data.repository



import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.bdapimodulo6wallet.data.local.WalletDatabase
import com.example.bdapimodulo6wallet.data.model.TransactionDataRequest
import com.example.bdapimodulo6wallet.data.network.api.WalletApiService
import com.example.bdapimodulo6wallet.data.response.TransactionDataResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [30])
class AlkeWalletRepositoryImplIntegrationTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context
    private lateinit var database: WalletDatabase
    private lateinit var repository: AlkeWalletRepositoryImpl
    private val apiService = Mockito.mock(WalletApiService::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, WalletDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = AlkeWalletRepositoryImpl(context).apply {
            walletDatabase = this@AlkeWalletRepositoryImplIntegrationTest.database
            apiService = this@AlkeWalletRepositoryImplIntegrationTest.apiService
        }
    }

    @After
    fun teardown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testIngresarDinero() = runTest(testDispatcher) {
        val accountId = 1
        val type = "credit"
        val concept = "deposit"
        val amount = 1000
        val transactionRequest = TransactionDataRequest(type, concept, amount)
        val date = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val dateString = dateFormat.format(date)

        val transactionResponse = TransactionDataResponse(
            id = 1,
            accountId = accountId,
            type = type,
            concept = concept,
            amount = amount,
            userId = 1,
            to_account_id = 2,
            date = dateString
        )

        `when`(apiService.ingresarDinero(accountId, transactionRequest)).thenReturn(transactionResponse)

        repository.ingresarDinero(accountId, type, concept, amount)

        val transactions = repository.getAllTransactions()
        Assert.assertEquals(1, transactions.size)
        Assert.assertEquals(transactionResponse, transactions[0])
    }
}
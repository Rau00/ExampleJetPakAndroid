package technical.test.jetpack.network.interfaces

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import technical.test.jetpack.utils.Constants

interface ApiService {

    companion object Factory {

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder().apply {
                client(makeOkHttpClient())
                addCallAdapterFactory(CoroutineCallAdapterFactory())
                baseUrl(Constants.URL_BASE)
                addConverterFactory(MoshiConverterFactory.create())
            }.build()

            return retrofit.create(ApiInterface::class.java)
        }

        private fun makeOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor()).apply {
                    connectTimeout(120, TimeUnit.SECONDS)
                    readTimeout(120, TimeUnit.SECONDS)
                    writeTimeout(90, TimeUnit.SECONDS)
                }.build()
        }

        private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level =
                HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}


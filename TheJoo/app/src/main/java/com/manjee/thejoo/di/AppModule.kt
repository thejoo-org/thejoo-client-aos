package com.manjee.thejoo.di

import android.content.Context
import com.manjee.thejoo.TheJooPreference
import com.manjee.thejoo.api.TestApi
import com.manjee.thejoo.data.repository.TestRepository
import com.manjee.thejoo.data.repository.TestRepositoryImpl
import com.manjee.thejoo.util.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideUrlProvider() = UrlProvider()

    @Singleton
    @Provides
    fun providerTheJooPreference(@ApplicationContext context: Context): TheJooPreference {
        return TheJooPreference(context)
    }

    @Provides
    @Singleton
    fun provideJsonOkHttpClient(theJooPreference: TheJooPreference): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .addHeader("platform", "AOS")

                if (theJooPreference.getUserToken().isNotEmpty()) {
                    request.addHeader("authorization", theJooPreference.getUserToken())
                }

                chain.proceed(request.build())
            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        urlProvider: UrlProvider
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTestApiService(retrofit: Retrofit): TestApi =
        retrofit.create(TestApi::class.java)

    @Singleton
    @Provides
    fun providerTestRepository(testApi: TestApi): TestRepository {
        return TestRepositoryImpl(testApi)
    }
}
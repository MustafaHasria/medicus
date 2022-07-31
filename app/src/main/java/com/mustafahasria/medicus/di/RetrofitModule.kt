package com.mustafahasria.medicus.di

import com.mustafahasria.medicus.api.MedicusApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                //.cache(Cache(File(NerdApplication.getContext()!!.cacheDir, "someIdentifier"), 50000))
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
                .baseUrl(MedicusApi.BASE_URL)
                //.addConverterFactory(MoshiConverterFactory.create(moshi))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

    @Singleton
    @Provides
    fun providesIntegrateApi(retrofit: Retrofit): MedicusApi = retrofit.create(MedicusApi::class.java)
}
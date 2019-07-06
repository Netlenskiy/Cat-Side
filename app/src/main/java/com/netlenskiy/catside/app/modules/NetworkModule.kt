package com.netlenskiy.catside.app.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.netlenskiy.catside.BuildConfig
import com.netlenskiy.catside.app.TheCatAPI
import com.netlenskiy.catside.app.interceptors.XApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule(private val url: String, private val apiKey: String) {

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesAPI(retrofit: Retrofit): TheCatAPI {
        return retrofit.create(TheCatAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        client.addInterceptor(loggingInterceptor)
        client.addInterceptor(XApiKeyInterceptor(apiKey))
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }
}
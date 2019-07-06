package com.netlenskiy.catside.app.interceptors

import com.netlenskiy.catside.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class XApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (BuildConfig.DEBUG) {
            Timber.d("X-API-KEY: $apiKey")
        }
        val modifiedRequest = originalRequest.newBuilder()
            .header("x-api-key", apiKey)
            .method(originalRequest.method(), originalRequest.body())
            .build()
        return chain.proceed(modifiedRequest)
    }

}
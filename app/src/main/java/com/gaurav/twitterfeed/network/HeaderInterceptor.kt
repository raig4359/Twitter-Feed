package com.gaurav.twitterfeed.network

 import okhttp3.Interceptor
import retrofit2.Response
import java.io.IOException


class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    fun intercept(chain: Interceptor.Chain): Response<*> {
        val original = chain.request()

        val request = original.newBuilder()
                .header("api-key", BuildConfig.API_KEY)
                .method(original.method(), original.body())
                .build()

        return chain.proceed(request)
    }
}
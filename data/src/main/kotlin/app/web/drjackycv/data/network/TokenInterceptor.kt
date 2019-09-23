package app.web.drjackycv.data.network

import app.web.drjackycv.data.network.constant.DataConstants.ACCESS_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url
        val newHttpUrl = httpUrl.newBuilder().addQueryParameter("userKey", ACCESS_KEY)
            .build() //TODO: Request automatically for new token when this one expired
        val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}
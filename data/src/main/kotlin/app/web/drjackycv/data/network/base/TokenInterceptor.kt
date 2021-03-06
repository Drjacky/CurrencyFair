package app.web.drjackycv.data.network.base

import app.web.drjackycv.data.network.constant.DataConstants.ACCESS_KEY_KEY
import app.web.drjackycv.data.network.constant.DataConstants.ACCESS_KEY_VALUE
import app.web.drjackycv.data.network.constant.DataConstants.NO_JSON_CALLBACK_KEY
import app.web.drjackycv.data.network.constant.DataConstants.NO_JSON_CALLBACK_VALUE
import app.web.drjackycv.data.network.constant.DataConstants.REQUEST_FORMAT_KEY
import app.web.drjackycv.data.network.constant.DataConstants.REQUEST_FORMAT_VALUE
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url
        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter(ACCESS_KEY_KEY, ACCESS_KEY_VALUE)
            .addQueryParameter(REQUEST_FORMAT_KEY, REQUEST_FORMAT_VALUE)
            .addQueryParameter(NO_JSON_CALLBACK_KEY, NO_JSON_CALLBACK_VALUE)
            .build()
        val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}
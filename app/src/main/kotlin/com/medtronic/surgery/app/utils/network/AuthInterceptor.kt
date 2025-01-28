package com.medtronic.surgery.app.utils.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor to add the Authorization header to the request
 * The token is retrieved from the AuthRepository where you can store and retrieve the token securely
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // you can store and retrieve the token securely here from your repository
        // val token = authRepository.getStoredAccessToken()
        val request = chain
            .request()
            .newBuilder().apply {
                // header("Authorization", "Bearer ")
            }
            .build()
        return chain.proceed(request)
    }
}
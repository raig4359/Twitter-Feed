package com.gaurav.twitterfeed.network

import android.util.Base64
import android.util.Log
import com.gaurav.twitterfeed.BuildConfig
import com.gaurav.twitterfeed.SharedPrefUtils
import com.gaurav.twitterfeed.TwitterFeedApp
import com.google.common.escape.Escaper
import com.google.common.net.UrlEscapers
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class HeaderInterceptor : Interceptor {

    private var oAuthConsumerKey = BuildConfig.CONSUMER_KEY
    private var oAuthNonce: String = ""
    private var oAuthSignature: String = ""
    private var oAuthSignatureMethod = "HMAC-SHA1"
    private var oAuthTimestamp: String = ""
    private var oAuthToken: String = ""
    private var oAuthVersion: String = "1.0"

    private var headerValue: StringBuffer = StringBuffer("")

    private var esc: Escaper = UrlEscapers.urlFormParameterEscaper()

    init {

        oAuthConsumerKey = BuildConfig.CONSUMER_KEY
        oAuthNonce = getUniqueStringId()
        oAuthToken = SharedPrefUtils.getAuthToken(TwitterFeedApp.appContext!!)
        oAuthTimestamp = getOauthTimeStamp()
        oAuthSignature = computeSignature(getBaseString(), getSigningKey())

        headerValue.append("OAuth ")
            .append(esc.escape("oauth_consumer_key")).append("=").append("\"").append(esc.escape(oAuthConsumerKey)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_nonce")).append("=").append("\"").append(esc.escape(oAuthNonce)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_signature")).append("=").append("\"").append(esc.escape(oAuthSignature)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_signature_method")).append("=").append("\"").append(esc.escape(oAuthSignatureMethod)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_timestamp")).append("=").append("\"").append(esc.escape(oAuthTimestamp)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_token")).append("=").append("\"").append(esc.escape(oAuthToken)).append("\"")
            .append(", ")
            .append(esc.escape("oauth_version")).append("=").append("\"").append(esc.escape(oAuthVersion)).append("\"")

        Log.e("header", " - - $headerValue")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .addHeader("Authorization", headerValue.toString())
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }

    private fun getBaseString(): String {
        val params = StringBuffer("")

        params.append(esc.escape("include_entities")).append("=").append(esc.escape("true"))
            .append("&")
            .append(esc.escape("oauth_consumer_key")).append("=").append(esc.escape(oAuthConsumerKey))
            .append("&")
            .append(esc.escape("oauth_nonce")).append("=").append(esc.escape(oAuthNonce))
            .append("&")
            .append(esc.escape("oauth_signature_method")).append("=").append(esc.escape(oAuthSignatureMethod))
            .append("&")
            .append(esc.escape("oauth_timestamp")).append("=").append(esc.escape(oAuthTimestamp))
            .append("&")
            .append(esc.escape("oauth_token")).append("=").append(esc.escape(oAuthToken))
            .append("&")
            .append(esc.escape("oauth_version")).append("=").append(esc.escape("1.0"))

        Log.e("ParameterStr", " - - $params")

        val signatureBase = StringBuffer("")

        signatureBase.append("GET")
            .append("&")
            .append(esc.escape("https://api.twitter.com/1.1/statuses/home_timeline.json"))
            .append("&")
            .append(esc.escape(params.toString()))

        Log.e("signatureBase", " - - $signatureBase")

        return signatureBase.toString()
    }

    private fun getSigningKey(): String {
        return StringBuffer("").append(esc.escape(BuildConfig.CONSUMER_SECRET))
            .append("&")
            .append(esc.escape(SharedPrefUtils.getAuthSecret(TwitterFeedApp.appContext!!)))
            .toString()
    }

    private fun getOauthTimeStamp(): String {
        oAuthTimestamp = (System.currentTimeMillis() / 1000).toString()
        return oAuthTimestamp
    }

    private fun getUniqueStringId(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }

    private fun computeSignature(baseString: String, keyString: String): String {
        var secretKey: SecretKey? = null
        val keyBytes = keyString.toByteArray()
        secretKey = SecretKeySpec(keyBytes, "HmacSHA1")
        val mac = Mac.getInstance("HmacSHA1")
        mac.init(secretKey)
        val text = baseString.toByteArray()
        return String(Base64.encode(mac.doFinal(text), Base64.DEFAULT))
    }

}
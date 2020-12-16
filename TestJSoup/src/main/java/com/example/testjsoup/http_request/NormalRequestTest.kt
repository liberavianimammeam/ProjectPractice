package com.example.testjsoup.http_request

import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import java.io.UnsupportedEncodingException

class NormalRequestTest(
    method: Int,
    url: String,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
): StringRequest(method, url, listener, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse): Response<String>? {
        val parsed: String
        parsed = try {
            String(response.data).plus(HttpHeaderParser.parseCharset(response.headers, "UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            // Since minSdkVersion = 8, we can't call
            // new String(response.data, Charset.defaultCharset())
            // So suppress the warning instead.
            String(response.data)
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response))
    }

}
package no.mhl.kotdoc.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface DocService {

    @Streaming
    @GET
    suspend fun getMarkdownForFile(@Url file: String): ResponseBody

}
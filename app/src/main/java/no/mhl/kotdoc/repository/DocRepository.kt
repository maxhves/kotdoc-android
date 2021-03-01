package no.mhl.kotdoc.repository

import no.mhl.kotdoc.data.remote.DocService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DocRepository @Inject constructor(
    private val docService: DocService
) {

    fun getRemoteFile(fileName: String) {
        docService.getMarkdownForFile(fileName).enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // On failure
                val t = call
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                // On response
                val t = call
            }
        })
    }

}
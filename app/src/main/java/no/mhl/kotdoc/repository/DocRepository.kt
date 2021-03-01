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

    suspend fun getRemoteFile(fileName: String): ResponseBody {
        return docService.getMarkdownForFile(fileName)
    }

}
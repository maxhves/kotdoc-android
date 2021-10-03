package no.mhl.kotdoc.repository

import no.mhl.kotdoc.data.remote.DocService
import okhttp3.ResponseBody
import javax.inject.Inject

class DocRepository @Inject constructor(
    private val docService: DocService
) {

    suspend fun getRemoteFile(fileName: String): ResponseBody {
        return docService.getMarkdownForFile(fileName)
    }

}
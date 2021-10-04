package no.mhl.kotdoc.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import no.mhl.kotdoc.data.remote.DocService
import javax.inject.Inject

class DocRepository @Inject constructor(
    private val docService: DocService
) {

    suspend fun getRemoteFile(fileName: String): Flow<List<String>> = callbackFlow {
        val document = docService.getMarkdownForFile(fileName)
        trySend(document.charStream().readLines().toList())
        awaitClose { document.close() }
    }

}
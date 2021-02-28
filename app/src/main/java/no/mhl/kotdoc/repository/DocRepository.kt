package no.mhl.kotdoc.repository

import no.mhl.kotdoc.data.remote.DocService
import javax.inject.Inject

class DocRepository
@Inject
constructor(
    private val docService: DocService
) {

}
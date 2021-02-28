package no.mhl.kotdoc.ui.home

import androidx.lifecycle.ViewModel
import no.mhl.kotdoc.repository.DocRepository
import javax.inject.Inject

class HomeViewModel
@Inject
constructor(
    private val docRepository: DocRepository
) : ViewModel() {

}
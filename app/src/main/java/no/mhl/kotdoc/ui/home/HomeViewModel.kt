package no.mhl.kotdoc.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import no.mhl.kotdoc.repository.DocRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val docRepository: DocRepository
) : ViewModel() {

    fun testGetFile() {
        viewModelScope.launch {
            docRepository.getRemoteFile("whatsnew1430.md")
        }
    }

}
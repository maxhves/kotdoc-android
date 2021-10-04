package no.mhl.kotdoc.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import no.mhl.kotdoc.repository.DocRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val docRepository: DocRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HomeViewState>(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                docRepository.getRemoteFile("whatsnew1430.md")
            ) { doc ->
                HomeViewState(doc[0])
            }.collect {
                _state.value = it
            }
        }
    }

}
package com.naufalnibros.kagura.viewmodels

import com.naufalnibros.kagura.domain.interactor.SkinInteractor
import com.naufalnibros.kagura.domain.models.Skin
import com.naufalnibros.kagura.domain.state.StateSkin
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SkinViewModel(private val interactor: SkinInteractor): ViewModel() {

    private val _skins = MutableStateFlow<StateSkin>(StateSkin.OnInit)
    val skins = _skins.asStateFlow()

    private val _select = MutableStateFlow(Skin())
    val select = _select.asStateFlow()

    init {
        load()
    }

    fun load() {
        _skins.value = StateSkin.OnLoading
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val data = interactor.getSkins()
                _skins.value = StateSkin.OnSuccess(data)
            } catch (e: Exception) {
                e.printStackTrace()
                _skins.value = StateSkin.OnError(e.message.orEmpty())
            }
        }
    }

    fun selected(item: Skin) {
        _select.value = item
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
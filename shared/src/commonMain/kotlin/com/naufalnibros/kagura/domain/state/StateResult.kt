package com.naufalnibros.kagura.domain.state

sealed interface StateResult<out R> {
    object OnInit: StateResult<Nothing>
    object OnLoading: StateResult<Nothing>
    data class OnSuccess<out T>(val data: T) : StateResult<T>
    data class OnError(val message: String) : StateResult<Nothing>
}
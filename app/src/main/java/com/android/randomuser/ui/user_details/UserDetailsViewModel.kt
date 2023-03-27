package com.android.randomuser.ui.user_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.randomuser.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val state: SavedStateHandle
) :
    ViewModel() {

    private val userResult = MutableStateFlow<UserDetailsResult>(Loading)

    init {
        val userId = UserDetailsFragmentArgs.fromSavedStateHandle(state).userId
        viewModelScope.launch {
            val result = repository.getUser(userId).map { UserDetails.fromDataLayerUser(it) }
            if (result.isSuccess) {
                result.getOrNull()?.let { userResult.emit(Success(it)) }
            } else {
                result.exceptionOrNull()?.let { userResult.emit(Fail(it)) }
            }
        }
    }

    fun observeUserDetailsResult(): Flow<UserDetailsResult> = userResult
}
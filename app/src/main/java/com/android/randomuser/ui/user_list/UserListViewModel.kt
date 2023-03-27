package com.android.randomuser.ui.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.randomuser.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private val errorEvent = Channel<String>()
    private val users = MutableStateFlow<List<UserListItem>>(emptyList())
    private val history = usersRepository.observeHistory()
        .map { it.map { user -> UserListItem.fromDataLayerUser(user) } }

    suspend fun loadUsers(userCount: Int) {
        val result = usersRepository.fetchUsers(userCount)
        if (result.isSuccess) {
            users.emit(result.getOrNull()?.map { UserListItem.fromDataLayerUser(it) }?: emptyList())
        } else {
            result.exceptionOrNull()?.message?.let { message -> errorEvent.send(message) }
        }
    }

    fun observeUsers(): Flow<List<UserListItem>> = users
    fun observeHistory(): Flow<List<UserListItem>> = history
    fun observeErrors(): Flow<String> = errorEvent.receiveAsFlow()
}
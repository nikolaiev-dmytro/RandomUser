package com.android.randomuser.ui.user_list

import androidx.lifecycle.ViewModel
import com.android.randomuser.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

}
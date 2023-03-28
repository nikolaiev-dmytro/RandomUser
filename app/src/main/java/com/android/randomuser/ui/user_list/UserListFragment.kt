package com.android.randomuser.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.randomuser.databinding.FragmentUserListBinding
import com.android.randomuser.ui.base.BaseUsersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : BaseUsersFragment<FragmentUserListBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentUserListBinding.inflate(inflater, container, false)

    override fun getUsersRecyclerView() = binding.rvUsersList

    override fun initUI() {
        super.initUI()
        binding.btLoadUsers.setOnClickListener {
            val userCount = binding.etUsersCount.text.toString().toIntOrNull() ?: 0
            lifecycleScope.launch { viewModel.loadUsers(userCount) }
        }
    }

    override fun initObservers() {
        super.initObservers()
        lifecycleScope.launch {
            viewModel.observeUsers().collectLatest {
                adapter.submitList(it)
            }
        }
    }
}
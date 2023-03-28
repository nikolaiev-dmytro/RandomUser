package com.android.randomuser.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.randomuser.databinding.FragmentHistoryBinding
import com.android.randomuser.ui.base.BaseUsersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : BaseUsersFragment<FragmentHistoryBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHistoryBinding.inflate(inflater, container, false)

    override fun getUsersRecyclerView() = binding.rvHistory

    override fun initObservers() {
        super.initObservers()
        lifecycleScope.launch {
            viewModel.observeHistory().collectLatest {
                adapter.submitList(it)
            }
        }
    }
}
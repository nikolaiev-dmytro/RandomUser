package com.android.randomuser.ui.user_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.randomuser.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    val binding get() = _binding!!
    private val viewModel by activityViewModels<UserListViewModel>()
    private val adapter by lazy { UserListAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserListBinding.inflate(layoutInflater)
        binding.rvUsersList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
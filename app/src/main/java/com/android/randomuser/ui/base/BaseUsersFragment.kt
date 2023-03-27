package com.android.randomuser.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.randomuser.R
import com.android.randomuser.ui.user_list.UserListAdapter
import com.android.randomuser.ui.user_list.UserListItem
import com.android.randomuser.ui.user_list.UserListViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch

abstract class BaseUsersFragment<T : ViewDataBinding> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!
    protected val viewModel by activityViewModels<UserListViewModel>()
    protected val adapter by lazy { UserListAdapter(::onUserClicked) }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun getUsersRecyclerView(): RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun initUI() {
        getUsersRecyclerView().adapter = adapter
        getUsersRecyclerView().addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                MaterialDividerItemDecoration.VERTICAL
            ).apply {
                setDividerInsetEndResource(requireContext(), R.dimen.doubleMargin)
                setDividerInsetStartResource(requireContext(), R.dimen.doubleMargin)
                setDividerColorResource(requireContext(), R.color.secondText)
                setDividerThicknessResource(requireContext(), R.dimen.userListDividerHeight)
            })
    }

    protected open fun initObservers() {
        lifecycleScope.launch {
            viewModel.observeErrors().collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onUserClicked(userListItem: UserListItem) {}
}
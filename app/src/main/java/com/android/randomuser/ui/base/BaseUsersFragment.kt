package com.android.randomuser.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.randomuser.R
import com.android.randomuser.ui.user_details.UserDetailsFragmentArgs
import com.android.randomuser.ui.user_list.UserListAdapter
import com.android.randomuser.ui.user_list.UserListItem
import com.android.randomuser.ui.user_list.UserListViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch

abstract class BaseUsersFragment<T : ViewDataBinding> : BaseFragment<T>() {

    protected val viewModel by activityViewModels<UserListViewModel>()
    protected val adapter by lazy { UserListAdapter(::onUserClicked) }

    abstract fun getUsersRecyclerView(): RecyclerView

    override fun initUI() {
        super.initUI()
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

    override fun initObservers() {
        super.initObservers()
        lifecycleScope.launch {
            viewModel.observeErrors().collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onUserClicked(userListItem: UserListItem) {
        findNavController().navigate(
            R.id.userDetails,
            UserDetailsFragmentArgs.Builder(userListItem.userId).build().toBundle()
        )
    }
}
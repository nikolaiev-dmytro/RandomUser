package com.android.randomuser.ui.user_details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.randomuser.databinding.FragmentUserDetailsBinding
import com.android.randomuser.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>() {
    private val viewModel by viewModels<UserDetailsViewModel>()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)

    override fun initObservers() {
        super.initObservers()
        lifecycleScope.launch {
            viewModel.observeUserDetailsResult().collect { result ->
                when (result) {
                    is Success -> binding.user = result.userDetails
                    is Fail -> result.exception.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
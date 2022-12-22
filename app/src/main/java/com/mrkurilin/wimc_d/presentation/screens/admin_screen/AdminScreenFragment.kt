package com.mrkurilin.wimc_d.presentation.screens.admin_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrkurilin.wimc_d.R

class AdminScreenFragment : Fragment(R.layout.admin_screen_fragment) {

    private val viewModel by viewModels<AdminScreenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
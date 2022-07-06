package com.pptg.e_measure.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.databinding.FragmentNotificationBinding
import com.pptg.e_measure.databinding.FragmentSettingsBinding
import com.pptg.e_measure.ui.settings.SettingsViewModel

class NotificationsFragment: Fragment() {
    private lateinit var viewModel: NotificationsViewModel
    private var _binding: FragmentNotificationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.notification()

        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvNotification.layoutManager = layoutManager
        binding.rvNotification.adapter = viewModel.adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
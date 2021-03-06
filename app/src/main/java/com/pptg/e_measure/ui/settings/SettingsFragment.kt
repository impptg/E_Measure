package com.pptg.e_measure.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {
    private lateinit var viewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter:SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.notification()

        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvSettings.layoutManager = layoutManager
        adapter = SettingsAdapter(this,viewModel.itemList)
        binding.rvSettings.adapter = adapter

        return root
    }
    override fun onStop() {
        super.onStop()
        viewModel.itemList.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
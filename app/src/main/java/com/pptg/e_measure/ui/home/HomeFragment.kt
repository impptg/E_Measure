package com.pptg.e_measure.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.FragmentHomeBinding
import com.pptg.e_measure.ui.search.SearchActivity
import java.lang.Exception
import kotlin.concurrent.thread

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = HomeAdapter(this,viewModel.mList)

        // viewModel.Task()
        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvHome.layoutManager = layoutManager
        binding.rvHome.adapter = adapter

        viewModel.mLiveList.observe(viewLifecycleOwner, Observer { mLiveList ->
            if(mLiveList != null){
                Toast.makeText(EMApplication.context,mLiveList.toString(),Toast.LENGTH_SHORT).show()
                viewModel.mList = mLiveList
                adapter.mList = mLiveList
                adapter.notifyDataSetChanged()
            }else{

            }
            binding.slHome.isRefreshing = false
        })

        binding.slHome.setOnRefreshListener {
            viewModel.refresh()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
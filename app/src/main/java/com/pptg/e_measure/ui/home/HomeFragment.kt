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

    private lateinit var model: HomeViewModel
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
        model =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = HomeAdapter(this,model.mList)

        // model.Task()
        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvHome.layoutManager = layoutManager
        binding.rvHome.adapter = adapter

        model.mLiveList.observe(viewLifecycleOwner, Observer { result ->
            if(result != null){
                Toast.makeText(EMApplication.context,result.toString(),Toast.LENGTH_SHORT).show()
                model.mList = result
                adapter.mList = result
                adapter.notifyDataSetChanged()
            }else{

            }
            binding.slHome.isRefreshing = false
        })

        binding.slHome.setOnRefreshListener {
            model.refresh()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
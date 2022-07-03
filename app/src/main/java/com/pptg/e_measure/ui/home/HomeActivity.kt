package com.pptg.e_measure.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.R
import com.pptg.e_measure.adapter.TaskAdapter
import com.pptg.e_measure.bean.TaskBeanData
import com.pptg.e_measure.databinding.ActivityHomeBinding
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.ui.login.LoginViewModel

class HomeActivity : AppCompatActivity() {

    // lateinit var adapter:TaskAdapter
    val viewModel by lazy{ ViewModelProvider(this).get(HomeViewModel::class.java)}
    val viewBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.Task()
        setContentView(viewBinding.root)
       // Thread.sleep(1000)
        val layoutManager = LinearLayoutManager(this)
        // adapter = TaskAdapter(viewModel.mList)
        viewBinding.rvHome.adapter = viewModel.adapter
        viewBinding.rvHome.layoutManager = layoutManager

    }
}
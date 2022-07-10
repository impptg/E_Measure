package com.pptg.e_measure.ui.measure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.databinding.ActivityMeasureBinding
import com.pptg.e_measure.ui.login.LoginViewModel

class MeasureActivity : AppCompatActivity() {

    val model by lazy{ ViewModelProvider(this).get(MeasureViewModel::class.java)}
    val binding by lazy { ActivityMeasureBinding.inflate(layoutInflater) }
    private lateinit var adapter:MeasureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val TaskID = intent.getStringExtra(EMApplication.TASK_ID)

        if (TaskID != null) {
            model.DecodeTask(TaskID)
        }

        adapter = MeasureAdapter(model.mList)
        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvMeasure.layoutManager = layoutManager
        binding.rvMeasure.adapter = adapter
    }
}
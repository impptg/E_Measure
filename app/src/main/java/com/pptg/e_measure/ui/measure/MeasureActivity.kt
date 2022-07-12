package com.pptg.e_measure.ui.measure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.databinding.ActivityMeasureBinding
import com.pptg.e_measure.ui.login.LoginViewModel
import com.pptg.e_measure.ui.scan.ScanActivity

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

        binding.tbMeasure.title = "数据测量"

        adapter = MeasureAdapter(model.mList)
        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvMeasure.layoutManager = layoutManager
        binding.rvMeasure.adapter = adapter

        binding.fabMeasure.setOnClickListener{
            val intent = Intent(this,ScanActivity::class.java)
            startActivity(intent)
        }
    }


}
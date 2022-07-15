package com.pptg.e_measure.ui.measure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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

    val model by lazy { ViewModelProvider(this).get(MeasureViewModel::class.java) }
    val binding by lazy { ActivityMeasureBinding.inflate(layoutInflater) }
    private lateinit var adapter: MeasureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val TaskID = intent.getStringExtra(EMApplication.TASK_ID)

        setSupportActionBar(binding.tbMeasure)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (TaskID != null) {
            model.DecodeTask(TaskID)
        }

        binding.tbMeasure.title = "数据测量"

        adapter = MeasureAdapter(model.mList)

        adapter.setOnItemClickListener(object : MeasureAdapter.OnItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                Toast.makeText(this@MeasureActivity, pos.toString(), Toast.LENGTH_SHORT).show()
                model.mPos = pos
            }
        })

        binding.tvMeasureValue.setOnClickListener {
            model.mList[model.mPos].value = "20"
            adapter.notifyDataSetChanged()
            binding.rvMeasure.scrollToPosition(model.ScrollPos())
        }

        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvMeasure.layoutManager = layoutManager
        binding.rvMeasure.adapter = adapter

        binding.fabMeasure.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.btn_measure_save -> {
                model.save()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.measure_toolbar_menu, menu)
        return true
    }
}
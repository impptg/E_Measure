package com.pptg.e_measure.ui.scan

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityScanBinding
import com.pptg.e_measure.ui.measure.MeasureActivity

class ScanActivity : AppCompatActivity() {
    val binding by lazy { ActivityScanBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(ScanViewModel::class.java) }
    lateinit var adapter:ScanAdapter
    lateinit var mHandler:Handler
    lateinit var mBluetoothAdapter:BluetoothAdapter
    var TASKID = ""

    private var mScanning:Boolean = false

    companion object{
        const val REQUEST_ENABLE_BT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mHandler = Handler()

        TASKID = intent.getStringExtra(EMApplication.TASK_ID).toString()


        // 检查设备是否支持BLE
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 通过系统的蓝牙管理器获取蓝牙适配器
        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = bluetoothManager.adapter

        // 检查蓝牙是否可用
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "蓝牙不可用", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // 确保启动蓝牙
        if (!mBluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        // 配置ListView
        adapter = ScanAdapter(model.mList)
        binding.rvScan.adapter = adapter
        binding.rvScan.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object : ScanAdapter.OnItemClickListener{
            override fun onItemClick(view: View, pos: Int) {
                val device = adapter.mList[pos]
                device.let {
                    val intent = Intent(this@ScanActivity,MeasureActivity::class.java)
                    intent.apply {
                        putExtra(EMApplication.TASK_ID,TASKID)
                        putExtra(EMApplication.DEVICE_ADDRESS,it.address)
                        putExtra(EMApplication.DEVICE_NAME,it.name)
                        setResult(1,this)
                    }
                }
                if(mScanning){
                    mBluetoothAdapter.stopLeScan(mLeScanCallback)
                    mScanning = false
                }
                this@ScanActivity.finish()
            }
        })
        scanLeDevice(true)
    }

    override fun onPause() {
        super.onPause()
        scanLeDevice(false)
        model.mList.clear()
    }

    fun scanLeDevice(enable:Boolean){
        if(enable){
            // 10s后停止扫描
            mHandler.postDelayed({
                mScanning = false
                mBluetoothAdapter.stopLeScan(mLeScanCallback)
                invalidateOptionsMenu()
            }, 10000)

            mScanning = true
            mBluetoothAdapter.startLeScan(mLeScanCallback)
        }else{
            mScanning = false
            mBluetoothAdapter.stopLeScan(mLeScanCallback)
        }
    }

    // 扫描回调函数
    private val mLeScanCallback =
        LeScanCallback { device, rssi, scanRecord ->
            runOnUiThread {
                if (device.name != null) {
                    model.AddDevice(device)
                    adapter.notifyDataSetChanged()
                }
            }
        }
}
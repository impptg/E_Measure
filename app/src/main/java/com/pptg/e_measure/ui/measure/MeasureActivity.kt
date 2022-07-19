package com.pptg.e_measure.ui.measure

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
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
import com.pptg.e_measure.ble.BLEService
import com.pptg.e_measure.ble.SampleGatt
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.databinding.ActivityMeasureBinding
import com.pptg.e_measure.ui.login.LoginViewModel
import com.pptg.e_measure.ui.scan.ScanActivity
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap
import kotlin.math.log

class MeasureActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(MeasureViewModel::class.java) }
    val binding by lazy { ActivityMeasureBinding.inflate(layoutInflater) }
    lateinit var mDeviceName:String
    lateinit var mDeviceAddress:String
    private lateinit var adapter: MeasureAdapter
    private var mBLEService: BLEService? = null
    private var mConnected = false
    private var mGattCharacteristics = ArrayList<ArrayList<BluetoothGattCharacteristic>>()
    private val LIST_NAME = "NAME"
    private val LIST_UUID = "UUID"
    var TaskID = ""
    private var mNotifyCharacteristic: BluetoothGattCharacteristic? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        TaskID = intent.getStringExtra(EMApplication.TASK_ID).toString()
        mDeviceName = intent.getStringExtra(EMApplication.DEVICE_NAME).toString()
        mDeviceAddress = intent.getStringExtra(EMApplication.DEVICE_ADDRESS).toString()

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
//            model.mList[model.mPos].value = "20"
//            adapter.notifyDataSetChanged()
//            binding.rvMeasure.scrollToPosition(model.ScrollPos())
            if (mGattCharacteristics != null) {
                val characteristic = mGattCharacteristics[3][0]
                val charaProp = characteristic.properties
                if (charaProp or BluetoothGattCharacteristic.PROPERTY_READ > 0) {
                    // If there is an active notification on a characteristic, clear
                    // it first so it doesn't update the data field on the user interface.
                    if (mNotifyCharacteristic != null) {
                        mBLEService?.setCharacteristicNotification(
                            mNotifyCharacteristic!!, false
                        )
                        mNotifyCharacteristic = null
                    }
                    mBLEService?.readCharacteristic(characteristic)
                }
                if (charaProp or BluetoothGattCharacteristic.PROPERTY_NOTIFY > 0) {
                    mNotifyCharacteristic = characteristic
                    mBLEService?.setCharacteristicNotification(
                        characteristic, true
                    )
                }
            }
        }

        val layoutManager = LinearLayoutManager(EMApplication.context)
        binding.rvMeasure.layoutManager = layoutManager
        binding.rvMeasure.adapter = adapter

        binding.fabMeasure.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            intent.apply {
                putExtra(EMApplication.TASK_ID,TaskID)
            }
            startActivityForResult(intent,1)
        }
        
        val intent = Intent(this,BLEService::class.java)

        val bindState = bindService(intent,mServiceConnection, BIND_AUTO_CREATE)
        Log.d(TAG, "onCreate: bindState = " + bindState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            TaskID = data.getStringExtra(EMApplication.TASK_ID).toString()
            mDeviceName =
                data.getStringExtra(EMApplication.DEVICE_NAME)!!
            mDeviceAddress =
                data.getStringExtra(EMApplication.DEVICE_ADDRESS)!!
        }
        Log.d(TAG, "onActivityResult: ")
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter())
        if (mBLEService != null && mDeviceAddress != null) {
            try {
                val result = mBLEService!!.connect(mDeviceAddress)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private val mGattUpdateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            // 连接
            if (BLEService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true
                //updateConnectionState(R.string.connected);
                invalidateOptionsMenu()
            } else if (BLEService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false
                //updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu()
            } else if (BLEService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // 在用户界面上显示所有支持的服务和特征。
                displayGattServices(mBLEService!!.getSupportedGattServices() as List<BluetoothGattService>?)
                //
//                List<BluetoothGattService> gattServices = mBLEService.getSupportedGattServices();
////                mNotifyCharacteristic = gattServices.get
                Log.d("pptg", "onReceive: 发现")
                // setNotify();
            } else if (BLEService.ACTION_DATA_AVAILABLE.equals(action)) {
                // 显示数据
                Log.d("pptg", "onReceive: 接收")
                try {
                    displayData(intent.getStringExtra(BLEService.EXTRA_DATA))
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun displayData(data: String?) {
        if (data != null) {
            val ppDataErr = data.substring(0, data.indexOf("0"))
            val ppData = data.substring(ppDataErr.length, data.length)
            val valStr = ppData.substring(14, 20).replace(" ".toRegex(), "")
            var disInt = valStr.toInt(16)
            if (ppData.substring(21, 23) == "01") {
                disInt = -disInt
            }
            val b = disInt
            val a = b.toFloat() / 100
            binding.tvMeasureValue.setText(java.lang.Float.toString(a))
        }
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private fun displayGattServices(gattServices: List<BluetoothGattService>?) {
        if (gattServices == null) return
        var uuid: String? = null
        val unknownServiceString = "unService"
        val unknownCharaString ="uncharacter"
        val gattServiceData = ArrayList<HashMap<String, String>>()
        val gattCharacteristicData = ArrayList<ArrayList<HashMap<String, String>>>()
        mGattCharacteristics = ArrayList<ArrayList<BluetoothGattCharacteristic>>()

        // Loops through available GATT Services.
        for (gattService in gattServices) {
            var currentServiceData = HashMap<String, String>()
            uuid = gattService.uuid.toString()
            currentServiceData[LIST_NAME] = SampleGatt.lookup(uuid, unknownServiceString)
            currentServiceData[LIST_UUID] = uuid
            gattServiceData.add(currentServiceData)
            val gattCharacteristicGroupData = ArrayList<HashMap<String, String>>()
            val gattCharacteristics = gattService.characteristics
            val charas = ArrayList<BluetoothGattCharacteristic>()

            // Loops through available Characteristics.
            for (gattCharacteristic in gattCharacteristics) {
                charas.add(gattCharacteristic)
                val currentCharaData = HashMap<String, String>()
                uuid = gattCharacteristic.uuid.toString()
                currentCharaData[LIST_NAME] = SampleGatt.lookup(uuid, unknownCharaString)
                currentCharaData[LIST_UUID] = uuid
                gattCharacteristicGroupData.add(currentCharaData)
            }
            mGattCharacteristics.add(charas)
            gattCharacteristicData.add(gattCharacteristicGroupData)
        }
        binding.tvMeasureValue.performClick()
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
    
    // BLE
    // 管理服务的生命周期
    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, service: IBinder) {
            mBLEService = (service as BLEService.LocalBinder).getService()
            if (!mBLEService!!.initialize()) {
                Log.e(MeasureActivity.TAG, "Unable to initialize Bluetooth")
                finish()
            }
            // Automatically connects to the device upon successful start-up initialization.
            try {
                mBLEService!!.connect(mDeviceAddress)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Log.d(MeasureActivity.TAG, "onServiceDisconnected: ")
            mBLEService = null
        }
    }

    private fun makeGattUpdateIntentFilter(): IntentFilter? {
        val intentFilter = IntentFilter()
        intentFilter.addAction(BLEService.ACTION_GATT_CONNECTED)
        intentFilter.addAction(BLEService.ACTION_GATT_DISCONNECTED)
        intentFilter.addAction(BLEService.ACTION_GATT_SERVICES_DISCOVERED)
        intentFilter.addAction(BLEService.ACTION_DATA_AVAILABLE)
        return intentFilter
    }
    
    
    companion object {
        private const val TAG = "MeasureActivity"
    }
}
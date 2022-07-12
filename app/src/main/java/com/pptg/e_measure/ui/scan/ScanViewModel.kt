package com.pptg.e_measure.ui.scan

import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.ViewModel

class ScanViewModel():ViewModel() {
    companion object {
        private const val TAG = "ScanViewModel"
    }
    var mList: MutableList<BluetoothDevice> = ArrayList()

    fun AddDevice(device:BluetoothDevice){
        if(device !in mList){
            mList.add(device)
            Log.d(TAG, "AddDevice: ")
        }
    }

    fun ClearDevice(){
        mList.clear()
    }
}
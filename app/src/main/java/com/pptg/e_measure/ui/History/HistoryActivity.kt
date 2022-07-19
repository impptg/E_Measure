package com.pptg.e_measure.ui.History

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityChangePwdBinding
import com.pptg.e_measure.databinding.ActivityHistoryBinding
import com.pptg.e_measure.ui.changePswd.ChangePswdViewModel

class HistoryActivity : AppCompatActivity() ,View.OnClickListener{
    val model by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }
    val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }
    private lateinit var adapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.tbHistory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter(this,model.mList)
        binding.historyRecord.adapter = adapter
        binding.historyRecord.layoutManager = layoutManager
    }
    //菜单的响应事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                Log.d(TAG,"点击返回按钮")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //自动隐藏键盘
    @CallSuper
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN){
            //获得当前得到的焦点view,一般情况下就是EditText
            val  view = currentFocus
            if (isShouldHideInput(view,ev)){
                hideSoftInput(view!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun isShouldHideInput(v: View?, event: MotionEvent) : Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0,0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = (left + v.getWidth())
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    fun hideSoftInput(token: IBinder?) {
        if (token != null) {
            val im: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    companion object {
        private const val TAG = "HistoryActivity"
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
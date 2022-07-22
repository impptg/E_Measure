package com.pptg.e_measure.ui.History

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.bean.HistoryBean
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
        adapter = HistoryAdapter(model.mList,model.isSelect.value,model.mSelectList)
        binding.historyRecord.adapter = adapter
        binding.historyRecord.layoutManager = layoutManager

        // 监听item点击
        adapter.setOnItemClickListener(object :HistoryAdapter.OnItemClickListener{
            override fun onItemClick(view: View, pos: Int) {
                if(model.isSelect.value == HistoryEnum.Select.START){
                    // TODO 选择
                    model.selectItem(pos)
                }else{
                    // TODO 进入详情

                }
            }

            override fun onItemLongClick(view: View, pos: Int): Boolean {
                model.changeSelectStatus()
                return true
            }
        })

        // 监听选择状态
        model.isSelect.observe(this,{
            when(it){
                HistoryEnum.Select.INIT -> {
                    // 底部配置
                    binding.cvHistory.visibility = View.GONE
                    // CheckBox
                    adapter.isSelect = it
                    adapter.notifyDataSetChanged()

                }
                HistoryEnum.Select.REFRESH -> {
                    adapter.mList = model.mList
                    adapter.notifyDataSetChanged()
                }
                else -> {
                    // 底部配置
                    binding.cvHistory.visibility = View.VISIBLE
                    // CheckBox
                    adapter.isSelect = it
                    adapter.notifyDataSetChanged()
                }
            }
        })

        // 监听删除状态
        model.isDelete.observe(this,{
            when(it){
                HistoryEnum.Delete.INIT -> {}
                HistoryEnum.Delete.START -> {}
                HistoryEnum.Delete.SUCCESS -> {}
                HistoryEnum.Delete.FAILED -> {}
            }
        })

        binding.cbHistory.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_toolbar_menu,menu)
        return true
    }

    //菜单的响应事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.search -> {
                return true
            }
            R.id.manage -> {
                model.changeSelectStatus()
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
        when (p0?.id){
            R.id.cb_history -> {
                val cb = p0 as CheckBox
                if(cb.isChecked){
                    model.selectAll()
                }else{
                    model.clearAll()
                }
            }
            R.id.btn_delete -> {
                model.delete()
                adapter.notifyDataSetChanged()
            }
            R.id.btn_cancel -> {
                model.changeSelectStatus()
            }
        }
    }
}
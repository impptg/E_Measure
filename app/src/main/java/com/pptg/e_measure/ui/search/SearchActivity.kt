package com.pptg.e_measure.ui.search

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivitySearchBinding
import com.pptg.e_measure.ui.home.HomeAdapter

class SearchActivity : AppCompatActivity() ,View.OnClickListener{
    val model by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }
    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private lateinit var adapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.searchBtn.setOnClickListener(this)
        binding.searchContent.setText(model.searchContent)
        val layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(this,model.mSearchList)
        binding.searchResult.adapter = adapter
        binding.searchResult.layoutManager = layoutManager

        model.isSearch.observe(this,{
            when(it){
                SearchEnum.Init -> {}
                SearchEnum.START -> {
                    binding.pbSearch.visibility = View.VISIBLE
                    model.mSearchList.clear()
                }
                SearchEnum.SUCCESS -> {
                    binding.pbSearch.visibility = View.GONE
                    adapter.searchList = model.mSearchList
                    adapter.notifyDataSetChanged()
                }
                SearchEnum.FAILED -> {
                    binding.pbSearch.visibility = View.GONE
                }
            }
        })

        binding.searchContent.addTextChangedListener {
            model.searchContent = binding.searchContent.text.toString()
            model.search()
        }
    }

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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.search_btn -> {
                finish()
            }
        }
    }
}
package com.pptg.e_measure.ui.changePswd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pptg.e_measure.R
import android.content.Context
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import com.pptg.e_measure.databinding.ActivityChangePwdBinding

class ChangePswdActivity : AppCompatActivity(),View.OnClickListener{

    val model by lazy { ViewModelProvider(this).get(ChangePswdViewModel::class.java) }
    val binding by lazy { ActivityChangePwdBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.changePswdButton.setOnClickListener(this)
        binding.olderPswd.setText(model.older_pswd)
        binding.newPswd.setText(model.new_pswd)
        binding.newPswd1.setText(model.new_pswd1)
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
            R.id.changePswd_button -> {
                model.older_pswd = binding.olderPswd.text.toString()
                model.new_pswd = binding.newPswd.text.toString()
                model.new_pswd1 = binding.newPswd1.text.toString()
                model.changePswd()
            }
        }
    }

    companion object {
        private const val TAG = "ChangePswdActivity"
    }
}
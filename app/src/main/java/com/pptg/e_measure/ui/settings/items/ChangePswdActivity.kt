package com.pptg.e_measure.ui.settings.items

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pptg.e_measure.R
import android.content.Context
import android.os.IBinder
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.databinding.ActivityChangePwdBinding
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.network.ApiNet
import com.pptg.e_measure.network.ServiceCreator
import com.pptg.e_measure.network.response.ChangePswdResponse
import com.pptg.e_measure.network.response.LoginResponse
import com.pptg.e_measure.ui.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ChangePswdActivity : AppCompatActivity(),View.OnClickListener{

    val viewmodel by lazy { ViewModelProvider(this).get(ChangePswdViewModel::class.java) }
    val viewBinding by lazy { ActivityChangePwdBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.changePswdButton.setOnClickListener(this)
        viewBinding.olderPswd.setText(viewmodel.older_pswd)
        viewBinding.newPswd.setText(viewmodel.new_pswd)
        viewBinding.newPswd1.setText(viewmodel.new_pswd1)
        viewBinding.textView9.setText(viewmodel.user_name)
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
                viewmodel.older_pswd = viewBinding.olderPswd.text.toString()
                viewmodel.new_pswd = viewBinding.newPswd.text.toString()
                viewmodel.new_pswd1 = viewBinding.newPswd1.text.toString()
                val prefs = getSharedPreferences("user_name",Context.MODE_PRIVATE)
                viewmodel.changePswd(p0,prefs)
            }
        }
    }

    companion object {
        private const val TAG = "ChangePswdActivity"
    }
}
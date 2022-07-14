package com.pptg.e_measure.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    val viewModel by lazy{ ViewModelProvider(this).get(LoginViewModel::class.java)}
    val viewBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.button.setOnClickListener(this)
        viewBinding.preview.setOnClickListener(this)
        viewBinding.editText.setText(viewModel.user_id)
        viewBinding.editText2.setText(viewModel.user_pswd)

        viewModel.isFinished.observe(this, {
            if(it) finish()
        })

    }

    @CallSuper
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN){
            //获得当前得到的焦点view,一般情况下就是EditText
            val  view = currentFocus
            if (isShouldHideInput(view,ev)){
                hideSoftInput(view!!.windowToken) //隐藏软键盘
                view.clearFocus()
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
        when(p0?.id){
            R.id.button ->{
                //TODO 登陆相关
                viewModel.user_id = viewBinding.editText.text.toString()
                viewModel.user_pswd = viewBinding.editText2.text.toString()
                viewModel.Login(p0)
            }
            R.id.preview ->{
                if(viewModel.isPreview) {
                    Log.d(TAG, "onClick: 不可见")
                    Log.d(TAG, "onClick: "+viewBinding.editText2.inputType)
                    p0.setBackgroundResource(R.drawable.ic_preview_close)
                    viewModel.isPreview = false
                    viewBinding.editText2.transformationMethod = PasswordTransformationMethod.getInstance()
                    viewBinding.editText2.setSelection(viewBinding.editText2.text.length)
                }else{
                    Log.d(TAG, "onClick: 可见")
                    Log.d(TAG, "onClick: "+viewBinding.editText2.inputType)
                    p0.setBackgroundResource(R.drawable.ic_preview_open)
                    viewModel.isPreview = true
                    viewBinding.editText2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    viewBinding.editText2.setSelection(viewBinding.editText2.text.length)
                }
            }
            null -> Toast.makeText(this,"NULL",Toast.LENGTH_SHORT).show()
        }
    }
    
    companion object {
        private const val TAG = "LoginActivity"
    }
}
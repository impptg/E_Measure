package com.pptg.e_measure.ui.login

import android.content.Context
import android.content.Intent
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
import com.pptg.e_measure.MainActivity
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    val model by lazy{ ViewModelProvider(this).get(LoginViewModel::class.java)}
    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener(this)
        binding.preview.setOnClickListener(this)
        binding.editText.setText(model.user_id)
        binding.editText2.setText(model.user_pswd)
        binding.checkBox.isChecked = model.user_read

        model.isLogin.observe(this,{
            when(it){
                LoginEnum.Init -> {
                    // 初始状态
                }
                LoginEnum.START -> {
                    // 开始登陆
                    binding.pbLogin.visibility = View.VISIBLE
                }
                LoginEnum.SUCCESS -> {
                    // 登陆成功
                    binding.pbLogin.visibility = View.GONE
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    this@LoginActivity.finish()
                }
                LoginEnum.FAILED -> {
                    // 登陆失败
                    binding.pbLogin.visibility = View.GONE
                    Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
                }
            }
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
                model.user_id = binding.editText.text.toString()
                model.user_pswd = binding.editText2.text.toString()
                model.Login()
            }
            R.id.preview ->{
                if(model.isPreview) {
                    Log.d(TAG, "onClick: 不可见")
                    Log.d(TAG, "onClick: "+binding.editText2.inputType)
                    p0.setBackgroundResource(R.drawable.ic_preview_close)
                    model.isPreview = false
                    binding.editText2.transformationMethod = PasswordTransformationMethod.getInstance()
                    binding.editText2.setSelection(binding.editText2.text.length)
                }else{
                    Log.d(TAG, "onClick: 可见")
                    Log.d(TAG, "onClick: "+binding.editText2.inputType)
                    p0.setBackgroundResource(R.drawable.ic_preview_open)
                    model.isPreview = true
                    binding.editText2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    binding.editText2.setSelection(binding.editText2.text.length)
                }
            }
            null -> Toast.makeText(this,"NULL",Toast.LENGTH_SHORT).show()
        }
    }
    
    companion object {
        private const val TAG = "LoginActivity"
    }
}
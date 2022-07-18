package com.pptg.e_measure.ui.changePswd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pptg.e_measure.R
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import com.pptg.e_measure.databinding.ActivityChangePwdBinding
import com.pptg.e_measure.repository.sp.UserSP
import com.pptg.e_measure.ui.login.LoginActivity

class ChangePswdActivity : AppCompatActivity(),View.OnClickListener{

    val model by lazy { ViewModelProvider(this).get(ChangePswdViewModel::class.java) }
    val binding by lazy { ActivityChangePwdBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.changeButton.setOnClickListener(this)
        binding.etOld.setText(model.older_pswd)
        binding.etNew.setText(model.new_pswd)
        binding.etNew2.setText(model.new_pswd1)
        binding.tvChange.setText(model.user_id)
        setSupportActionBar(binding.tbPswd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        observeChangePswd()
    }

    fun observeChangePswd(){
        model.isChanged.observe(this,{
            when(it){
                ChangedEnum.Init->{}
                ChangedEnum.START->{
                    binding.pbChange.visibility = View.VISIBLE
                }
                ChangedEnum.SUCCESS->{
                    // 修改成功
                    // 关动画
                    binding.pbChange.visibility = View.GONE
                    // 清除默认密码
                    UserSP.setUser {
                        putString(UserSP.USER_PSWD,"")
                    }
                    // 重新登陆，清空返回栈
                    val intent: Intent = Intent(this, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    // 杀掉页面
                    this@ChangePswdActivity.finish()
                }
                ChangedEnum.FAILED->{
                    binding.pbChange.visibility = View.GONE
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
                ChangedEnum.WRONG->{
                    binding.pbChange.visibility = View.GONE
                    Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show()
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
            R.id.change_button -> {
                model.older_pswd = binding.etOld.text.toString()
                model.new_pswd = binding.etNew.text.toString()
                model.new_pswd1 = binding.etNew2.text.toString()
                model.changePswd()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "ChangePswdActivity"
    }
}
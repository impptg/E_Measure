package com.pptg.e_measure.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(),View.OnClickListener {
    val context:Context = this
    val viewModel by lazy{ ViewModelProvider(this).get(LoginViewModel::class.java)}
    val viewBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.button.setOnClickListener(this)
        viewBinding.preview.setOnClickListener(this)
        viewBinding.editText.setText(viewModel.user_id)
        viewBinding.editText2.setText(viewModel.user_pswd)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button ->{
                //TODO 登陆相关
                viewModel.user_id = viewBinding.editText.text.toString()
                viewModel.user_pswd = viewBinding.editText2.text.toString()
                // viewModel.Task()
                viewModel.Login(p0)
                //Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show()
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
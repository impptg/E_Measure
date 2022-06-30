package com.pptg.e_measure.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(),View.OnClickListener {
    val viewModel by lazy{ ViewModelProvider(this).get(LoginViewModel::class.java)}
    val viewBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.button.setOnClickListener(this)
        viewBinding.editText.setText(viewModel.user_id)
        viewBinding.editText2.setText(viewModel.user_pswd)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button ->{
                //TODO 登陆相关
                viewModel.user_id = viewBinding.editText.text.toString()
                viewModel.user_pswd = viewBinding.editText2.text.toString()
                viewModel.Login()
                //Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show()
            }
            null -> Toast.makeText(this,"NULL",Toast.LENGTH_SHORT).show()
        }
    }

}
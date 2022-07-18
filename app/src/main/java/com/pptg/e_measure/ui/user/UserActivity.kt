package com.pptg.e_measure.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.ActivityLoginBinding
import com.pptg.e_measure.databinding.ActivityUserBinding
import com.pptg.e_measure.repository.sp.UserSP
import com.pptg.e_measure.ui.login.LoginViewModel
import com.pptg.e_measure.view.OnStateChangedListener

class UserActivity : AppCompatActivity() {
    val model by lazy{ ViewModelProvider(this).get(UserViewModel::class.java)}
    val binding by lazy { ActivityUserBinding.inflate(layoutInflater) }

    companion object {
        private const val TAG = "UserActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.ctlUser.title = UserSP.getUser(UserSP.USER_ID)
        setSupportActionBar(binding.tbUser)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.ctlUser.setOnStateChangedListener(object : OnStateChangedListener {
            override fun onExpanded() {
                Log.d(TAG, "onExpanded: ")
            }
            override fun onCollapsed() {
                binding.ivUserIcon.visibility = View.GONE
                Log.d(TAG, "onCollapsed: ")
            }

            override fun onInternediateFromExpand() {
                Log.d(TAG, "onInternediateFromExpand: ")
            }

            override fun onInternediateFromCollapsed() {
                binding.ivUserIcon.visibility = View.VISIBLE
                Log.d(TAG, "onInternediateFromCollapsed: ")
            }
            override fun onInternediate() {
                Log.d(TAG, "onInternediate: ")
            }

        })
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
}
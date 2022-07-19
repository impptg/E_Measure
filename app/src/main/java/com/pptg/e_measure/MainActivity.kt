package com.pptg.e_measure

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pptg.e_measure.databinding.ActivityMainBinding
import com.pptg.e_measure.ui.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

import com.pptg.e_measure.utils.BadgeUtil


class MainActivity : AppCompatActivity(),NavController.OnDestinationChangedListener {

    companion object{
        private const val TAG = "MainActivity"
    }

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    lateinit var mMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        setSupportActionBar(binding.tbMain)
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_main)
        navController.addOnDestinationChangedListener(this)
        setNavView()
    }

    fun setNavView(){
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_refresh ->{
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val mNavigation:BottomNavigationView = binding.navView
        val itemView:BottomNavigationMenuView = mNavigation.getChildAt(0) as BottomNavigationMenuView
        BadgeUtil.QBadge(this,itemView.getChildAt(1),4)

        mMenu = menu
        checkOptionMenu()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Log.d(TAG, "onDestinationChanged: ")
        when(destination.id){
            R.id.navigation_home ->{
                model.optionMenuOn = true
                checkOptionMenu()
            }
            R.id.navigation_dashboard ->{
                model.optionMenuOn = false
                Log.d(TAG, "onDestinationChanged: false")
                checkOptionMenu()
            }
            R.id.navigation_settings ->{
                model.optionMenuOn = false
                checkOptionMenu()
            }
        }
    }

    fun checkOptionMenu() {
        if (this::mMenu.isInitialized) {
            if (model.optionMenuOn) {
                for (i in 0 until mMenu.size()) {
                    mMenu.getItem(i).setVisible(true)
                    mMenu.getItem(i).setEnabled(true)
                }
            } else {
                Log.d(TAG, "checkOptionMenu: false")
                for (i in 0 until mMenu.size()) {
                    mMenu.getItem(i).setVisible(false)
                    mMenu.getItem(i).setEnabled(false)
                }
            }
        }
    }

//    override fun onKeyDown(keyCode: Int, ev: KeyEvent): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK)
//            Toast.makeText(EMApplication.context, "NULL", Toast.LENGTH_SHORT).show()
////            System.exit(0)
////            exitBy2Click() //调用双击退出函数
//        return false
//    }
//    override fun onKeyDown(keyCode: Int, ev: KeyEvent): Boolean {
//        var exitTime = 0
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            // 判断两次点击间隔时间
//            if((System.currentTimeMillis()-exitTime)>2000){
//                Toast.makeText(EMApplication.context,"再次返回程序退出！",Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis().toInt(); // 设置第一次点击时间
//            }else{
//                //finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode,ev);
//    }
}
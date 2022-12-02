package com.jarvis.weatherj.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.jarvis.weatherj.presentation.common.Constant
import com.jarvis.weatherj.R
import com.jarvis.weatherj.databinding.ActivityMainBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import com.jarvis.weatherj.presentation.bmiother.DetailTodayFragment
import com.jarvis.weatherj.presentation.home.HomeFragment
import com.jarvis.weatherj.presentation.profile.ProfileFragment
import com.jarvis.weatherj.presentation.week.WeekFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private var isBackPress = false
    private var homeFragment: HomeFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var weekFragment: WeekFragment? = null
    private var detailTodayFragment: DetailTodayFragment? = null
    private val fragments: MutableList<Fragment> = arrayListOf()
    private var currentIndex: Int = 0

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    override fun setUpViews() {
        initFragment()
        clickShowFragment(Constant.KEY_HOME)
        setOnClickView()
    }

    private fun setOnClickView() {

    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        weekFragment = WeekFragment()
        detailTodayFragment = DetailTodayFragment()

        fragments.add(homeFragment ?: HomeFragment())
        fragments.add(profileFragment?: ProfileFragment())
        fragments.add(weekFragment?: WeekFragment())
        fragments.add(detailTodayFragment?: DetailTodayFragment())

        val size = fragments.size
        val supportFragmentManager = supportFragmentManager

        for (index in 0 until size) {
            val fragment = fragments[index]
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            if (!fragment.isAdded) {
                fragmentTransaction.add(R.id.frag, fragment, "fragment$index")
            }
            if (index != 0) {
                fragmentTransaction.hide(fragment).commitAllowingStateLoss()
            } else {
                fragmentTransaction.commitAllowingStateLoss()
            }
        }
    }

    private fun clickShowFragment(position: Int) {
        try {
            val targetFragment = fragments[position]
            val currentFragment: Fragment = getCurrentFragment()
            if (currentFragment.isAdded) {
                currentFragment.onPause()
            }
            if (!isFinishing) {
                supportFragmentManager.beginTransaction().hide(currentFragment).show(targetFragment)
                    .commitAllowingStateLoss()
                currentIndex = position
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrentFragment(): Fragment {
        return fragments[currentIndex]
    }

    override fun observeData() {
        super.observeData()
    }


    private fun restartActivity() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }


    override fun onBackPressed() {
        if (this.currentIndex != Constant.KEY_HOME) {
            this.viewModel.tempFrag.value = 1
            clickShowFragment(Constant.KEY_HOME)
            return
        }
        if (!isBackPress) {
//            Toast.makeText(this, resources.getString(R.string.home_exit), Toast.LENGTH_SHORT).show()
            isBackPress = true
            Handler(Looper.getMainLooper()).postDelayed({
                isBackPress = false
            }, 2000)
            return
        }
        super.onBackPressed()
    }
}

package com.example.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.nycschools.di.NYCApplication
import com.example.nycschools.model.local.SchoolSatEntity
import com.example.nycschools.view.SchoolListDisplay
import com.example.nycschools.viewmodel.NYCViewModel
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity: AppCompatActivity() {

    @Inject lateinit var viewModel: NYCViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NYCApplication.component.inject( this )

        viewModel.schoolSat.observe(this) {
            // todo create the fragment SchoolListDisplay (layout, recyclerview, adapter/viewholder)
            // todo in the fragment instance pass the Parcelable.
            // todo supportFragmentManager.beginTransaction()
            // todo modify the ActivityMainBinding define the container.
            initFragment(it)
        }
    }

    private fun initFragment(data: List<SchoolSatEntity>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SchoolListDisplay.newInstance(data))
            .commit()
    }
}
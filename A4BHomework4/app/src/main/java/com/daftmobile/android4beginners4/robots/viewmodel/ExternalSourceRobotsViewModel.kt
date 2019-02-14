package com.daftmobile.android4beginners4.robots.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daftmobile.android4beginners4.robots.model.ListRobotsDataSource
import com.daftmobile.android4beginners4.robots.model.RobotsDataSource

class ExternalSourceRobotsViewModel(
        private val robotDataSource: RobotsDataSource = ListRobotsDataSource()
) : ViewModel(), RobotsViewModel {



    private val mutableLiveData: MutableLiveData<String> = MutableLiveData()

    override fun getRobotList(): LiveData<String> = mutableLiveData

    override fun addRobot() {
        robotDataSource.addNew()
        mutableLiveData.value = robotDataSource.getRobots().joinToString("\n")//.toString()
    }

    override fun ascendingSort() {
        robotDataSource.sortAscending()
        mutableLiveData.value = robotDataSource.getRobots().joinToString("\n")
    }

    override fun descendingSort() {
        robotDataSource.sortDescending()
        mutableLiveData.value = robotDataSource.getRobots().joinToString("\n")
    }
}

package com.daftmobile.android4beginners4.robots.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.daftmobile.android4beginners4.robots.model.Robot
import com.daftmobile.android4beginners4.robots.model.RobotGenerator

class LiveDataRobotsViewModel: RobotsViewModel, ViewModel() {

    private val mutableLiveData: MutableLiveData<MutableList<Robot>> = MutableLiveData()

    override fun getRobotList(): LiveData<String> = Transformations.map(mutableLiveData) { list: List<Robot>? -> list.toString() }
    //LiveData udostepnia na zewnatrz tutaj
    //zwraca nowy obiekt i mapuje go wrzucajac do nowej listy

    override fun addRobot() {
        val mutableList = mutableLiveData.value ?: mutableListOf() //nowa lista
        mutableList.add(RobotGenerator.generate())
        mutableLiveData.value = mutableList
    }

    override fun ascendingSort() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun descendingSort() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

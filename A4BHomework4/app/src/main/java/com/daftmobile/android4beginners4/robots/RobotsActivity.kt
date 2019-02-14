package com.daftmobile.android4beginners4.robots

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.daftmobile.android4beginners4.robots.R.id.ascendingSort
import com.daftmobile.android4beginners4.robots.viewmodel.ExternalSourceRobotsViewModel
import com.daftmobile.android4beginners4.robots.viewmodel.LiveDataRobotsViewModel
import com.daftmobile.android4beginners4.robots.viewmodel.RobotsViewModel
import kotlinx.android.synthetic.main.activity_robots.*

class RobotsActivity : AppCompatActivity() {
    private lateinit var viewModel: RobotsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robots)

        //val dataSource = SqliteDataSource(this.applicationContext) //i activity nie wie skad pochodzi info

        //viewModel = LiveDataRobotsViewModel()
        viewModel = ViewModelProviders.of(this)
                /*.get(LiveDataRobotsViewModel::class.java)*/
                .get(ExternalSourceRobotsViewModel::class.java)


        viewModel.getRobotList().observe(this, Observer { robotTextView.text = it })

        addRobotFab.setOnClickListener {
            addNewItem()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.robots_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ascendingSort -> {
                println("Sortujemy rosnaco")
                item.isChecked = !item.isChecked
                viewModel.ascendingSort()
            }
            R.id.descendingSort -> {
                println("Sortujemy malejaco")
                item.isChecked = !item.isChecked
                viewModel.descendingSort()
            }

            else ->return super.onOptionsItemSelected(item)
        }
        return true
    }


    private fun addNewItem() {
        viewModel.addRobot()
    }
}

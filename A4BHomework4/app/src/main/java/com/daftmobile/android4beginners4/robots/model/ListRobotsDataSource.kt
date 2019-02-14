package com.daftmobile.android4beginners4.robots.model


class ListRobotsDataSource : RobotsDataSource {
    private val robots = mutableListOf<Robot>()
    private var flag = 2

    override fun getRobots(): List<Robot> {
        //robots.sortWith(Comparator)

        if (flag == 1) sortAscending()
        else if (flag == 0) sortDescending()

        return robots.toList()
    }

    override fun addNew(robot: Robot) {
        robots.add(robot)
    }

    override fun sortAscending(/*robots: MutableList<Robot>*/) {
        if (flag != 1) flag = 1

        fun selector(sel: Robot): String = sel.name

        robots.sortBy { selector(it) }
        robots.sortWith(object : Comparator<Robot> {
            override fun compare(r1: Robot, r2: Robot): Int {
                val sumR1 = "${r1.mood} ${r1.name}"
                val sumR2 = "${r2.mood} ${r2.name}"

                return when {
                    sumR1 >= sumR2 -> 1
                    else -> -1
                }
            }
        })
    }

    override fun sortDescending(/*robots: MutableList<Robot>*/) {
        if (flag != 0) flag = 0
        fun selector(sel: Robot): String = sel.name

        robots.sortBy { selector(it) }
        robots.sortWith(object : Comparator<Robot> {
            override fun compare(r1: Robot, r2: Robot): Int {
                val sumR1 = "${r1.mood} ${r1.name}"
                val sumR2 = "${r2.mood} ${r2.name}"

                return when {
                    sumR1 >= sumR2 -> -1
                    else -> 1
                }
            }
        })
    }
}

package com.project.ifoah.viewmodels.skidata

import java.util.*

data class SkiSession(
    val date: Date = Date(),
    val totalTurns: MutableList<Turn> = arrayListOf(),

    )

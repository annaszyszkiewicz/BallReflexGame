package edu.ib.ballreflexgame

import android.graphics.Canvas
import android.graphics.Paint

abstract class GameObject(
    var x: Double,
    var y: Double,
    val paint: Paint,
    var vx: Double,
    var vy: Double,
    var ax: Double,
    var ay: Double,
    var game: Game
) {

    abstract fun update()

    abstract fun draw(canvas: Canvas?)

}
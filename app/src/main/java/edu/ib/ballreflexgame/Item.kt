package edu.ib.ballreflexgame

import android.graphics.Paint

abstract class Item(
    x: Double,
    y: Double,
    paint: Paint,
    vx: Double,
    vy: Double,
    ax: Double,
    ay: Double,
) : GameObject(x, y, paint, vx, vy, ax, ay) {

    abstract fun collide(ball: Ball)
}
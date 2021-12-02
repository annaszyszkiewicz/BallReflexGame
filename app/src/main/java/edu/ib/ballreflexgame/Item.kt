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
    game: Game
) : GameObject(x, y, paint, vx, vy, ax, ay, game) {

    abstract fun collide(ball: Ball)
}
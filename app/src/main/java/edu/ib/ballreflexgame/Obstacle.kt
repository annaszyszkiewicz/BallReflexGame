package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class Obstacle(
    x: Double,
    y: Double,
    paint: Paint,
    vx: Double,
    vy: Double,
    ax: Double,
    ay: Double,
    var l: Double,
    var h: Double,
    game: Game
) : Item(x, y, paint, vx, vy, ax, ay, game) {

    private var collided: Boolean = false


    override fun collide(ball: Ball) {
        for (i in 0 until 359) {
            val xCollide = ball.x + ball.radius * sin(i.toDouble())
            val yCollide = ball.y + ball.radius * cos(i.toDouble())
            if (xCollide < x + l && yCollide < y + h && xCollide > x && yCollide > y) {
                game.v = 20.0
                game.obstacleTime = 2000
                game.gameObjects.remove(this)

                Log.d("EDUIB", "collision: ${hashCode()}")
                break
            }
        }
    }

    override fun update() {
        vy += ay
        y += vy
        if (y > 2500) {
            game.gameObjects.remove(this)
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawRect(
            x.toFloat(),
            y.toFloat(),
            (x + l).toFloat(),
            (y + h).toFloat(),
            this.paint
        )
    }
}
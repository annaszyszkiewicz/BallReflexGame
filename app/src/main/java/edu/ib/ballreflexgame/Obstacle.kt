package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class Obstacle(
    x: Double,
    y: Double,
    paint: Paint,
    vx: Double,
    vy: Double,
    ax: Double,
    ay: Double,
    var l: Double,
    var h: Double
) : Item(x, y, paint, vx, vy, ax, ay) {


    override fun collide(ball: Ball) {
        TODO("Not yet implemented")
    }

    override fun update() {
        x += vx
        y += vy
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawRect(
            x.toFloat(),
            y.toFloat(),
            (x+l).toFloat(),
            (y+h).toFloat(),
            this.paint
        )
    }
}
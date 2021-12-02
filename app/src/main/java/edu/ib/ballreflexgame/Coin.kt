package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class Coin(
    x: Double,
    y: Double,
    paint: Paint,
    vx: Double,
    vy: Double,
    ax: Double,
    ay: Double,
    var r: Double,
    game: Game
) : Item(x, y, paint, vx, vy, ax, ay, game) {

    override fun collide(ball: Ball) {

    }

    override fun update() {
        x += vx
        y += vy
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawCircle(
            this.x.toFloat(),
            this.y.toFloat(),
            this.r.toFloat(),
            this.paint
        )
    }
}
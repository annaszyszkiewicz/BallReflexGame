package edu.ib.ballreflexgame

import android.graphics.Canvas
import android.graphics.Paint

class Ball(
    x: Double,
    y: Double,
    paint: Paint,
    vx: Double,
    vy: Double,
    ax: Double,
    ay: Double,
    var radius: Double
) : GameObject(x, y, paint, vx, vy, ax, ay) {


    /**
     * metoda odpowiadajaca za zasady fizyki w aplikacji
     * by kulka nie wyszla za ekran
     * by kulka odbijala się od granic ekranu z odpowiednia predkoscia
     */
    override fun update() {
        vx += ax
        if (vx > 50) vx = 50.0
        if (vx < -50) vx = -50.0

        x += vx
        if (x < radius) {
            x = radius
            vx *= -0.5
        }
        if (x > view.width - radius) {
            x = view.width - radius
            vx *= -0.5
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawCircle(
            this.x.toFloat(),
            this.y.toFloat(),
            this.radius.toFloat(),
            this.paint
        )
    }

}
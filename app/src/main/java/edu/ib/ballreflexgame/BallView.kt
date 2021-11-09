package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * rozszerzenie widoku
 */
class BallView(context: Context?) : View(context) {
    var viewWidth = 0
    var viewHeight = 0
    var ball: Ball

init {
    val ballPaint = Paint()
    ballPaint.color = Color.MAGENTA
    ball = Ball(this, 0.0, 0.0, 50.0, ballPaint)
}
    /**
     * metoda wykonujaca sie przy zmianie romiaru okna
     * ustawia pozycje kulki na srodku ekranu
     *
     * @param w    nowa szerokosc okna
     * @param h    nowa wysokosc okna
     * @param oldw stara szerokosc okna
     * @param oldh stara wysokosc okna
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        viewWidth = w
        viewHeight = h
        ball.x = (viewWidth / 2).toDouble()
        ball.y = (viewHeight / 2).toDouble()
    }

    /**
     * metoda rysujaca kulke
     *
     * @param canvas
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        ball.calculate()
        canvas.drawCircle(
            ball.x.toFloat(),
            ball.y.toFloat(),
            ball.radius.toFloat(),
            ball.paint
        )
        invalidate()
    }

    /**
     * kontruktor tworzacy widok
     *
     * @param context konteks
     */
    init {
        val ballPaint = Paint()
        ballPaint.color = Color.MAGENTA
        ball = Ball(this, 0.0, 0.0, 50.0, ballPaint)
    }
}
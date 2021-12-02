package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlin.properties.Delegates
import kotlin.random.Random


class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    internal var thread: GameThread
    internal var ball: Ball
    internal var gameObjects = ArrayList<GameObject>()
    private var lastTime: Long = 0
    var score = -1
    var obstacleTime = 5000


    internal var v: Double by Delegates.observable(2.0) { _, _, new ->
        kotlin.run {
            for (gameObject in gameObjects) {
                if (gameObject !is Ball) {
                    gameObject.vy = new
                }
            }
        }
    }

    init {
        this.holder.addCallback(this)
        thread = GameThread(holder, this)
        this.focusable = View.FOCUSABLE
        val ballPaint = Paint()
        ballPaint.color = Color.MAGENTA
        ball = Ball(540.0, 1200.0, ballPaint, 0.0, 0.0, 0.0, 0.0, 50.0, this)
        gameObjects.add(ball)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.running = false
                thread.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    fun update() {

        if (System.currentTimeMillis() - lastTime > obstacleTime) {
            score++
            var space = Random.nextDouble(4 * ball.radius, 8 * ball.radius)
            var l1 = Random.nextDouble(0.0, width.toDouble() - space)
            var l2 = width - space - l1
            var x2 = l1 + space
            var h = ball.radius * 2
            val testPaint = Paint()
            testPaint.color = Color.BLUE
            val obstacle1 = Obstacle(0.0, -h, testPaint, 0.0, v, 0.0, 0.0, l1, h, this)
            val obstacle2 = Obstacle(x2, -h, testPaint, 0.0, v, 0.0, 0.0, l2, h, this)
            gameObjects.add(obstacle1)
            gameObjects.add(obstacle2)
            lastTime = System.currentTimeMillis()
        } else {
            lastTime += 1
        }
        for (gameObject in gameObjects) {
            if (gameObject is Item) {
                gameObject.collide(ball)
            }
            gameObject.update()
        }

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        for (gameObject in gameObjects) {
            gameObject.draw(canvas)
        }
        val scorePaint = Paint()
        scorePaint.color = Color.WHITE
        scorePaint.textSize = 100.0F
        canvas?.drawText("Score: $score", (20.0).toFloat(), (height-50).toFloat(), scorePaint)
    }

}
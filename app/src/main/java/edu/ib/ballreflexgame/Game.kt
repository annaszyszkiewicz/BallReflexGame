package edu.ib.ballreflexgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View


class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private var thread : GameThread
    private var gameObjects = ArrayList<GameObject>()

    init{
        this.holder.addCallback(this)

        thread = GameThread(holder, this)

        this.focusable = View.FOCUSABLE
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
        val testPaint = Paint()
        testPaint.color = Color.MAGENTA
        var test = Obstacle(50.0, 100.0, testPaint,0.0,1.0,0.0,0.0,100.0,10.0)
        gameObjects.add(test)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry : Boolean = true
        while (retry){
            try {
                thread.running = false
                thread.join()
            } catch (e : InterruptedException){
                e.printStackTrace()
            }
            retry = false
        }
    }

    fun update() {
        for(gameObject in gameObjects){
            gameObject.update()
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        for(gameObject in gameObjects){
            gameObject.draw(canvas)
        }
    }

}
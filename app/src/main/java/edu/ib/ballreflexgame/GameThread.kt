package edu.ib.ballreflexgame

import android.graphics.Canvas
import android.view.SurfaceHolder
import java.lang.Exception

class GameThread(var surfaceHolder: SurfaceHolder, var game: Game) : Thread() {

    private var FPS: Int = 30
    private var avgFPS: Double = 0.0
    internal var running: Boolean = false

    companion object {
         var canvas: Canvas? = null
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        var targetTime: Long = (1000/FPS).toLong()

        while (running){
            startTime =System.nanoTime()
            canvas = null

            try{
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder){
                    this.game.update()
                    this.game.draw(canvas)
                }
            } catch (e : Exception){
                e.printStackTrace()
            } finally {
                if (canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e : Exception){
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime / 1000000)
            waitTime =targetTime - timeMillis

            try {
                sleep(waitTime)
            } catch (e : Exception){
                e.printStackTrace()
            }

            totalTime += System.nanoTime() - startTime
            frameCount++
            if(frameCount == FPS){
                avgFPS = 100.0 / ((totalTime / frameCount) / 1000000)
                frameCount = 0
                totalTime = 0
                System.out.println(avgFPS)
            }
        }
    }



}
package edu.ib.ballreflexgame

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        game = Game(this)
        setContentView(game)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager!!.registerListener(
            this,
            sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )

        val displayMetrics = DisplayMetrics()


        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels
    }

//    fun onClickStart(view: View) {
//        val ballPaint = Paint()
//        ballPaint.color = Color.MAGENTA
//        ball = Ball(540.0, 1200.0, ballPaint, 0.0, 0.0, 0.0, 0.0, 50.0)
//        setContentView(ball.view)
//
//        //obstacle = Obstacle(0.0, 0.0, ballPaint, 0.0,0.0,0.0,0.0, 50.0, 25.0)
//        //addContentView()
//
//        running = true
//
//    }

    /**
     * przyslonieta metoda wykonujaca sie przy zmianie ulozenia telefonu
     *
     * @param event zdarzenie
     */
    override fun onSensorChanged(event: SensorEvent) {
        val values = event.values
        val ax = values[0]
        game.ball.ax = (-ax * 0.05)
    }

    /**
     * przyslonieta metoda wykonujaca sie po wznowieniu dzialania
     */
    override fun onResume() {
        super.onResume()
        if (game.thread.running) {
            sensorManager!!.registerListener(
                this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    /**
     * przyslonieta metoda wykonujaca się po zatrzymaniu dzialania
     */
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    /**
     * metoda wykonujaca sie przy zmianie precyzji
     *
     * @param sensor   czujnik
     * @param accuracy precyzja
     */
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    companion object {
        private const val NANO_TO_SEC = 1.0 / 100000000000000
    }

}
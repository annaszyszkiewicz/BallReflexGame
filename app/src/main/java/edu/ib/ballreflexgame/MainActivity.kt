package edu.ib.ballreflexgame

import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() /*, SensorEventListener*/ {
    private var sensorManager: SensorManager? = null
    private var lastTime: Long = 0
    private var running: Boolean = false
    private lateinit var ball: Ball
    private lateinit var obstacle: Obstacle
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        game = Game(this)
        setContentView(game)
    }

//    fun onClickStart(view: View) {
//        val ballPaint = Paint()
//        ballPaint.color = Color.MAGENTA
//        ball = Ball(540.0, 1200.0, ballPaint, 0.0, 0.0, 0.0, 0.0, 50.0)
//        setContentView(ball.view)
//
//        //obstacle = Obstacle(0.0, 0.0, ballPaint, 0.0,0.0,0.0,0.0, 50.0, 25.0)
//        //addContentView()
//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        running = true
//        sensorManager!!.registerListener(
//            this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//            SensorManager.SENSOR_DELAY_GAME
//        )
//    }

    /**
     * przyslonieta metoda wykonujaca sie przy zmianie ulozenia telefonu
     *
     * @param event zdarzenie
     */
//    override fun onSensorChanged(event: SensorEvent) {
//        val values = event.values
//        val ax = values[0]
//        val timeStamp = event.timestamp.toFloat()
//        val actualTime = (timeStamp * NANO_TO_SEC).toFloat()
//        if (actualTime - lastTime > 0.05) {
//            ball!!.ax = (-ax * 0.1)
//            lastTime = actualTime.toLong()
//        }
//    }

    /**
     * przyslonieta metoda wykonujaca sie po wznowieniu dzialania
     */
//    override fun onResume() {
//        super.onResume()
//        if (running) {
//            sensorManager!!.registerListener(
//                this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                SensorManager.SENSOR_DELAY_GAME
//            )
//        }
//    }

    /**
     * przyslonieta metoda wykonujaca się po zatrzymaniu dzialania
     */
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }

    /**
     * metoda wykonujaca sie przy zmianie precyzji
     *
     * @param sensor   czujnik
     * @param accuracy precyzja
     */
//    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
//
//    companion object {
//        private const val NANO_TO_SEC = 1.0 / 100000000000000
//    }

}
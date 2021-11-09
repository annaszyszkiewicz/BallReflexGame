package edu.ib.ballreflexgame

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * klasa obslugujaca glowna aktywnosc
 */
class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var ballView: BallView? = null
    private var lastTime: Long = 0

    /**
     * przyslonieta metoda wykonujaca sie przy otwieraniu aplikacji
     *
     * @param savedInstanceState zapisywanie aktualnych informacji
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        ballView = BallView(this)
        setContentView(ballView)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    /**
     * przyslonieta metoda wykonujaca sie przy zmianie ulozenia telefonu
     *
     * @param event zdarzenie
     */
    override fun onSensorChanged(event: SensorEvent) {
        val values = event.values
        val ax = values[0]
        val timeStamp = event.timestamp.toFloat()
        val actualTime = (timeStamp * NANO_TO_SEC).toFloat()
        if (actualTime - lastTime > 0.05) {
            ballView?.ball!!.a = (-ax * 0.1)
            lastTime = actualTime.toLong()
        }
    }

    /**
     * przyslonieta metoda wykonujaca sie po wznowieniu dzialania
     */
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(
            this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )
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
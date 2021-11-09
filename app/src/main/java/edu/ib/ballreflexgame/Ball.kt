package edu.ib.ballreflexgame

import android.graphics.Paint
import android.view.View

/**
 * klasa opisujaca pilke
 */
class Ball
/**
 * konstruktor tworzacy pilke
 *
 * @param view   widok aplikacji
 * @param x      polozenie pilki w plaszczyznie x
 * @param y      polozenie pilki w plaszczyznie y
 * @param radius promien pilki
 * @param paint  odpowiada za kolor pilki
 */(private val view: View, var x: Double, var y: Double, var radius: Double, val paint: Paint) {
    var v = 0.0
    var a = 0.0

    /**
     * metoda odpowiadajaca za zasady fizyki w aplikacji
     * by kulka nie wyszla za ekran
     * by kulka odbijala się od granic ekranu z odpowiednia predkoscia
     */
    fun calculate() {
        v += a
        if (v > 50) v = 50.0
        if (v < -50) v = -50.0

        x += v
        if (x < radius) {
            x = radius
            v *= -0.5
        }
        if (x > view.width - radius) {
            x = view.width - radius
            v *= -0.5
        }
    }

}
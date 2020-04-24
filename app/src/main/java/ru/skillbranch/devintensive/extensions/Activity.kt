package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender

fun Activity.hideKeyboard(activity: Activity){
    try {

        val imm =
            activity.application.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
        imm.hideSoftInputFromWindow(
            getCurrentFocus()?.getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    } catch (e:Exception){
        println(e.stackTrace)
    }
}

fun Activity.isKeyboardOpen(activity: Activity):Boolean{
    try {
        val imm =
            activity.application.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager

        return imm.isAcceptingText()
    } catch (e:Exception){
        println(e.stackTrace)
        return false
    }
}

fun Activity.isKeyboardClosed(activity: Activity):Boolean{
    try {

        val imm =
            activity.application.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager

        return !imm.isAcceptingText()
    }catch (e:Exception){
        println(e.stackTrace)
        return false
    }
}

fun Activity.onSend(a: Activity, benderObj: Bender, messageEt: EditText, benderImage: ImageView, textTxt: TextView) {

    val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString()/*.toLowerCase()*/)
    messageEt.setText("")
    val (r, g, b) = color
    benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
    textTxt.text = phrase
    if (isKeyboardOpen(this)){hideKeyboard(this)}

}

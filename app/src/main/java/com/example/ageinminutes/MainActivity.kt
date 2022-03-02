package com.example.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnDatePicker)

        button.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener {
                   _, selectedYear, selectedMonth, selectedDayOfMonth ->
               val dateViewer = findViewById<TextView>(R.id.selectedDate)
               val minuteViewer = findViewById<TextView>(R.id.selectedDateInMinute)

               val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
               dateViewer.text = selectedDate
               val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
              val theDate = sdf.parse(selectedDate)
               val selectedDateInMinutes = theDate!!.time / 60000
               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
               val currentDateInMinutes = currentDate!!.time / 60000
               val differentInMinutes = currentDateInMinutes - selectedDateInMinutes
               minuteViewer.text = differentInMinutes.toString()
                                               },
            year,
            myCalender.get(Calendar.MONTH),
            myCalender.get(Calendar.DAY_OF_MONTH))
        dpd.datePicker.maxDate = Date().time + 86400000
        dpd.show()
    }
}
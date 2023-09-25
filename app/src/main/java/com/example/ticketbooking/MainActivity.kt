package com.example.ticketbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ticketbooking.databinding.ActivityMainBinding
import android.app.TimePickerDialog
import android.widget.TimePicker
import java.util.*
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tickets = arrayOf(
            "Ekonomi",
            "Eksekutif",
            "Luxury",
        )

        val cities = resources.getStringArray(R.array.cities)

        with(binding){
            val ticketsAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item , tickets)
            ticketsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spintickets.adapter = ticketsAdapter

            val citiesAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item , cities)
            citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spincities.adapter = citiesAdapter

            val mTimePicker: TimePickerDialog
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)

            mTimePicker = TimePickerDialog(this@MainActivity, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    selectedTime.setText(String.format("%d : %d", hourOfDay, minute))
                }
            }, hour, minute, false)

            selectTime.setOnClickListener({ v ->
                mTimePicker.show()
            })

            val currentTime = Calendar.getInstance()
            val year = currentTime.get(Calendar.YEAR)
            val month = currentTime.get(Calendar.MONTH)
            val day = currentTime.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this@MainActivity, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate.setText(String.format("%d / %d / %d", dayOfMonth, month + 1, year))
                }
            }, year, month, day);

            selectDate.setOnClickListener({ v ->
                datePicker.show()
            })

            with(binding){
                pesan.setOnClickListener{
                    val enteredDate = selectedDate.text.toString()
                    val message = "Pemesanan tiket untuk tanggal $enteredDate berhasil dilakukan!"
                    Toast.makeText(this@MainActivity,message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
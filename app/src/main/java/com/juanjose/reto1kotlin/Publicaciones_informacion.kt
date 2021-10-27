package com.juanjose.reto1kotlin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesInformacionBinding
import java.text.SimpleDateFormat
import java.util.*

class Publicaciones_informacion : Fragment() , DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    var buttonPressed = false

    private lateinit var binding : FragmentPublicacionesInformacionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPublicacionesInformacionBinding.inflate(inflater,container,false)
        var view = binding.root

        pickDate()

        // Inflate the layout for this fragment
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() = Publicaciones_informacion()
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(){

        binding.startBtn.setOnClickListener {
            pickerShow()
            this.buttonPressed = true
        }

        binding.endBtn.setOnClickListener {
            pickerShow()
            this.buttonPressed = false
        }

    }

    override fun onDateSet(view : DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(activity, this, hour, minute, true).show()
    }

    override fun onTimeSet(view : TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        if (this.buttonPressed){
            binding.startBtn.text = "$savedMinute $savedMonth $savedYear $savedHour:$savedMinute.PM"
        }else{
            binding.endBtn.text = "$savedMinute $savedMonth $savedYear $savedHour:$savedMinute.PM"
        }

    }

    private fun pickerShow(){
        getDateTimeCalendar()
        activity?.let { DatePickerDialog(it, this, year, month, day).show() }
    }

}
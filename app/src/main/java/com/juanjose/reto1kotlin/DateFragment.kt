package com.juanjose.reto1kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.juanjose.reto1kotlin.databinding.FragmentDateBinding
import java.util.*


class DateFragment : DialogFragment() {

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    private lateinit var binding : FragmentDateBinding
    private lateinit var listener : OnDateSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)

        binding = FragmentDateBinding.inflate(inflater,container,false)

        binding.datePicker.setOnDateChangeListener { calendarView, year, month, day ->
            this.year = year
            this.month = month
            this.day = day
        }

        binding.nextBtn.setOnClickListener {

            var calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, this.year)
            calendar.set(Calendar.MONTH, this.month)
            calendar.set(Calendar.DAY_OF_MONTH, this.day)
            calendar.set(Calendar.HOUR_OF_DAY, binding.timePicker.hour)
            calendar.set(Calendar.MINUTE, binding.timePicker.minute)
            listener.onDate(calendar.time.time)
            this.dismiss()
        }

        return view
    }

    fun setListener(listener : OnDateSelectedListener){
        this.listener = listener
    }

    interface OnDateSelectedListener{
        fun onDate(date : Long)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = DateFragment()
    }
}
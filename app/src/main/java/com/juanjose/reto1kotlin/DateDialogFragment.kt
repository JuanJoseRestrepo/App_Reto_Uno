package com.juanjose.reto1kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [DateDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DateDialogFragment : DialogFragment() {
    //State
    private var year: Int
    private var month: Int
    private var day: Int
    private var listener: OnDateSelectedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_date, container, false)
        val datePicker = view.findViewById<CalendarView>(R.id.datePicker)
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        val nextBtn = view.findViewById<Button>(R.id.nextBtn)

        //Para obtener los datos del calendar se debe manejar el evento
        datePicker.setOnDateChangeListener { root: CalendarView?, year: Int, month: Int, dayOfMonth: Int ->
            this.year = year
            this.month = month
            day = dayOfMonth
        }

        //El boton de NEXT recoge toda la información en un objeto calendar y por medio
        //de observer, se envia al fragmento CreateEventFragment
        nextBtn.setOnClickListener { v: View? ->
            val calendar = Calendar.getInstance()
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day
            calendar[Calendar.HOUR_OF_DAY] = timePicker.hour
            calendar[Calendar.MINUTE] = timePicker.minute
            listener!!.onDate(calendar.time.time)
            dismiss()
        }
        return view
    }

    fun setListener(listener: OnDateSelectedListener?) {
        this.listener = listener
    }

    //Callback
    interface OnDateSelectedListener {
        fun onDate(date: Long)
    }

    companion object {
        fun newInstance(): DateDialogFragment {
            val fragment = DateDialogFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    init {
        val c = Calendar.getInstance()
        year = c[Calendar.YEAR]
        month = c[Calendar.MONTH]
        day = c[Calendar.DAY_OF_MONTH]
    }
}
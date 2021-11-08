package com.juanjose.reto1kotlin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesInformacionBinding
import kotlinx.android.synthetic.main.fragment_publicaciones_informacion.*
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

    private var _binding : FragmentPublicacionesInformacionBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageUri: Uri
    private lateinit var eventName: String
    private lateinit var placeName: String

    //Listener
    var listener : OnNewPostListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPublicacionesInformacionBinding.inflate(inflater,container,false)
        var view = binding.root

        pickDate()

        binding.crearBtn.setOnClickListener {

            val sharedPreferences = requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

            val oldname = sharedPreferences.getString("NAME", "Inserte el nombre")
            val oldimage = sharedPreferences.getString("IMAGE", null)

            //Publicacion
            listener?.let {
                it.OnNewPost(eventAddressTV.text.toString(), startBtn.text.toString(), endBtn.text.toString(), Uri.parse(oldimage), editTextTextPostName2.text.toString(), oldname!!)
            }

        }

        binding.googleBtn.setOnClickListener{

            activity?.let{
                val intent = Intent (it, MapsActivity::class.java)
                it.startActivity(intent)
            }

        }

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Interfaces
    interface OnNewPostListener{
        fun OnNewPost(address : String, startDate : String, endDate : String, photo : Uri, eventName : String, placeName : String)
    }

    interface onMapListener{
        fun onNewMap()
    }

}
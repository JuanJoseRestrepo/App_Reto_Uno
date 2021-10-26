package com.juanjose.reto1kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesInformacionBinding
import java.text.SimpleDateFormat
import java.util.*

class Publicaciones_informacion : Fragment() {

    private var startDate : Long = 0
    private var endDate : Long = 0

    private lateinit var binding : FragmentPublicacionesInformacionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPublicacionesInformacionBinding.inflate(inflater,container,false)
        var view = binding.root

        binding.startBtn.setOnClickListener { view: View? ->
            defineStartDate(
                requireView()
            )
        }

        binding.endBtn.setOnClickListener { view: View? ->
            defineEndDate(
                requireView()
            )
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun defineStartDate(view: View) {
  /**      showDatePicker{ date ->
            startDate = date
            binding.startBtn
            binding.startBtn.setText(formatDate(startDate))
        }**/
    }

    private fun formatDate(date: Long): String? {
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        return sdf.format(Date(date))
    }

    private fun defineEndDate(view: View) {
        /**      showDatePicker{date->
            endDate = date;
            binding.endBtn.setText(formatDate(endDate));
        }**/
    }

    private fun showDatePicker(listener: DateDialogFragment.OnDateSelectedListener) {
        val dialog = DateDialogFragment()
        dialog.setListener(listener)
        dialog.show(requireActivity().supportFragmentManager, "dialog")
    }






    companion object {
        @JvmStatic
        fun newInstance() = Publicaciones_informacion()
    }
}
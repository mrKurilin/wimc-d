package com.mrkurilin.wimc_d.presentation.screens.plan_drive_screen

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_DESTINATIONS_KEY
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_PLANED_DRIVES_KEY
import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive
import com.mrkurilin.wimc_d.data.utils.MyValueEventListener

class PlanDriveFragment : Fragment(R.layout.plan_drive_screen_fragment) {

    private lateinit var timePicker: TimePicker
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        fillSpinners()

        timePicker.setIs24HourView(true)

        cancelButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        okButton.setOnClickListener {
            val to = toSpinner.selectedView.findViewById<TextView>(android.R.id.text1).text.toString()
            val from = fromSpinner.selectedView.findViewById<TextView>(android.R.id.text1).text.toString()

            if (to == from) {
                Toast.makeText(requireContext(), "Неверено указаны данные", Toast.LENGTH_LONG)
                    .show()
            } else {
                val planner = FirebaseAuth.getInstance().currentUser!!.displayName!!
                val hours = "%02d".format(timePicker.currentHour)
                val minutes = "%02d".format(timePicker.currentMinute)

                val time = "$hours:$minutes"
                Firebase.database.reference.child(REF_PLANED_DRIVES_KEY).push().setValue(
                    PlannedDrive(
                        planner = planner,
                        from = from,
                        to = to,
                        planTime = time
                    )
                )
                parentFragmentManager.popBackStack()
            }
        }

    }

    private fun fillSpinners() {
        Firebase.database.reference.child(REF_DESTINATIONS_KEY)
            .addListenerForSingleValueEvent(object : MyValueEventListener() {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val destinations = snapshot.getValue<HashMap<String, String>>()?.values?.toTypedArray()!!
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        destinations
                    )
                    fromSpinner.adapter = adapter
                    toSpinner.adapter = adapter
                }
            })
    }

    private fun initViews(view: View) {
        timePicker = view.findViewById(R.id.timepicker)
        fromSpinner = view.findViewById(R.id.from_spinner)
        toSpinner = view.findViewById(R.id.to_spinner)
        okButton = view.findViewById(R.id.button_ok)
        cancelButton = view.findViewById(R.id.cancel_button)
    }
}
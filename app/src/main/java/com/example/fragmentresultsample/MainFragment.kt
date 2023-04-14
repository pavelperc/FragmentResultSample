package com.example.fragmentresultsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main) {

    val contract = registerForActivityResult(StartActivityForResult()) {
        Toast.makeText(requireContext(), "Got result", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        buttonStartActivity.setOnClickListener {
            contract.launch(Intent(requireContext(), SecondActivity::class.java))
        }
    }
}
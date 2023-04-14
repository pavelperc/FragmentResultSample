package com.example.fragmentresultsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : RetainedFragment(R.layout.fragment_main) {

    private val contract = registerActivityLauncher {
        Toast.makeText(requireContext(), "Got result", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonStartActivity.setOnClickListener {
            contract.launch(Intent(requireContext(), SecondActivity::class.java))
        }
    }
}
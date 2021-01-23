package com.example.cityguideapp

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.synthetic.main.fragment_retailer_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up_second.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpSecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpSecondFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sharedElementEnterTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.change_bounds)
            sharedElementReturnTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.change_bounds)
        }
        return inflater.inflate(R.layout.fragment_sign_up_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_up_second_next_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (!validateGender() || !validateAge()) {
                    return
                }

                val selectedGenderButtonId = sign_up_second_radio_group.checkedRadioButtonId
                val gender = view.findViewById<RadioButton>(selectedGenderButtonId).text

                val day = sign_up_second_date_picker.dayOfMonth
                val month = sign_up_second_date_picker.month
                val year = sign_up_second_date_picker.year

                val date = "$day/$month/$year"

                val extras = FragmentNavigatorExtras(
                    signup_second_back_button to "transition_back_btn",
                    sign_up_second_next_btn to "transition_next_btn",
                    sign_up_second_login_btn to "transition_login_btn",
                    sign_up_second_title_text to "transition_title_text"
                )

                p0!!.findNavController().navigate(
                    R.id.action_signUpSecondFragment_to_lastSignUpFragment,
                    null,
                    null,
                    extras
                )
            }

        })
    }

    fun validateGender(): Boolean {
        if (sign_up_second_radio_group.checkedRadioButtonId == -1) {
            Toast.makeText(context, "Please Select Gender", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun validateAge(): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val userAge = sign_up_second_date_picker.year
        val validYears = currentYear - userAge

        if (validYears < 14) {
            Toast.makeText(context, "You are not eligible to apply to the app", Toast.LENGTH_SHORT)
                .show()
            return false

        } else {
            return true
        }
    }


}
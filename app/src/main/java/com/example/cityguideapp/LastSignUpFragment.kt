package com.example.cityguideapp

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_last__sign__up_.*


/**
 * A simple [Fragment] subclass.
 * Use the [LastSignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LastSignUpFragment : Fragment() {

    private val args: LastSignUpFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sharedElementEnterTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.change_bounds)
            sharedElementReturnTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.change_bounds)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last__sign__up_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fullName = args.fullName
        val username = args.userName
        val email = args.email
        val password = args.password
        val gender = args.gender
        val date = args.date

        sign_up_last_next_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (!verifyPhoneNumber()) {
                    return
                }
                val phoneNumber = verifyOtp()
                val action =
                    LastSignUpFragmentDirections.actionLastSignUpFragmentToVerifyOtpFragment(
                        fullName, username, email, password, gender, date, phoneNumber
                    )
                findNavController().navigate(action)
            }

        })
    }

    private fun verifyOtp(): String {
        val userEnteredPhoneNumber = last_sign_up_phone_number.editText!!.text.toString().trim()
        return "+${last_sign_up_country_code_picker.fullNumber}$userEnteredPhoneNumber"

    }

    private fun verifyPhoneNumber(): Boolean {
        val phoneNumber = last_sign_up_phone_number.editText!!.text.toString().trim()
        return if (phoneNumber.isEmpty()) {
            last_sign_up_phone_number.error = "Field Cannot be empty"
            false
        } else if (Patterns.PHONE.matcher(phoneNumber).matches()) {
            last_sign_up_phone_number.error = null
            last_sign_up_phone_number.isErrorEnabled = false
            true

        } else {
            false
        }
    }

}
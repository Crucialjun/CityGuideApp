package com.example.cityguideapp

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.synthetic.main.fragment_retailer_sign_up.*
import kotlinx.android.synthetic.main.fragment_retailer_sign_up_sign_in.*


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters


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
        return inflater.inflate(R.layout.fragment_retailer_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_up_next_btn.setOnClickListener { p0 ->
            if (!validateFullName() || !validateUserName() || !validateEmail() || !validatePassword()) {
                return@setOnClickListener
            }

            val extras = FragmentNavigatorExtras(
                signup_back_button to "transition_back_btn",
                sign_up_next_btn to "transition_next_btn",
                sign_up_login_btn to "transition_login_btn",
                sign_up_title_text to "transition_title_text"
            )

            p0!!.findNavController().navigate(
                R.id.action_signUpFragment_to_signUpSecondFragment,
                null,
                null,
                extras
            )
        }

    }

    fun validateFullName(): Boolean {
        val fullname = sign_up_full_name.editText!!.text.toString().trim()

        return if (fullname.isEmpty()) {
            sign_up_full_name.error = "Field Cannot be empty"
            false
        } else {
            sign_up_full_name.error = null
            sign_up_full_name.isErrorEnabled = false
            true
        }

    }

    fun validateUserName(): Boolean {
        val userName = sign_up_user_name.editText!!.text.toString().trim()
        val checkSpaces = "\\A\\w{1,20}\\z".toRegex()

        return if (userName.isEmpty()) {
            sign_up_user_name.error = "Field Cannot be empty"
            false
        } else if (userName.length > 20) {
            sign_up_user_name.error = "Username is too long"
            false
        } else if (!userName.matches(checkSpaces)) {
            sign_up_user_name.error = "No Spaces are allowed"
            false
        } else {
            sign_up_user_name.error = null
            sign_up_user_name.isErrorEnabled = false
            true
        }

    }

    fun validateEmail(): Boolean {
        val email = sign_up_email.editText!!.text.toString().trim()
        val checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+".toRegex()

        return if (email.isEmpty()) {
            sign_up_email.error = "Field Cannot be empty"
            false
        } else if (!email.matches(checkEmail)) {
            sign_up_email.error = "Invalid Email Address"
            false
        } else {
            sign_up_email.error = null
            sign_up_email.isErrorEnabled = false
            true
        }

    }

    fun validatePassword(): Boolean {
        val password = sign_up_password.editText!!.text.toString().trim()
        val checkpassWord = ("^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$").toRegex()

        return if (password.isEmpty()) {
            sign_up_password.error = "Field Cannot be empty"
            false
        } else if (!password.matches(checkpassWord)) {
            sign_up_password.error = "Invalid Password"
            false
        } else {
            sign_up_password.error = null
            sign_up_email.isErrorEnabled = false
            true
        }

    }
}
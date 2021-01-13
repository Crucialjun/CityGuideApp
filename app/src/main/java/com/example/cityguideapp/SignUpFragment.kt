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
}
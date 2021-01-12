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
import kotlinx.android.synthetic.main.fragment_sign_up_second.*



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


}
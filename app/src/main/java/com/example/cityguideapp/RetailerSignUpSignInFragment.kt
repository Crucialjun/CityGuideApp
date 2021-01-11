package com.example.cityguideapp

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.synthetic.main.fragment_retailer_sign_up_sign_in.*


class RetailerSignUpSignInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_retailer_sign_up_sign_in, container, false)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_btn.setOnClickListener { p0 ->
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                requireActivity(),
//                UtilPair.create(login_btn, "transition_login")
//            )
//
//
//            val extras = ActivityNavigatorExtras(options)

            val extras = FragmentNavigatorExtras(login_btn to "transition_login")

            p0!!.findNavController().navigate(
                R.id.action_retailerSignUpSignInFragment_to_loginRetailerFragment,
                null,
                null,
                extras
            )
        }
    }
}
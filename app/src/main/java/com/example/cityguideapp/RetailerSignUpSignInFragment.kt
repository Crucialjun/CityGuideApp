package com.example.cityguideapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_retailer_sign_up_sign_in.*
import androidx.core.util.Pair as UtilPair


class RetailerSignUpSignInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retailer_sign_up_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_btn.setOnClickListener { p0 ->
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                UtilPair.create(login_btn, "transition_login")
            )

            val extras = ActivityNavigatorExtras(options)

            p0!!.findNavController().navigate(
                R.id.action_retailerSignUpSignInFragment_to_loginRetailerFragment,
                null,
                null,
                extras
            )
        }
    }
}
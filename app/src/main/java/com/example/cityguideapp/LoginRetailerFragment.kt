package com.example.cityguideapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_login_retailer.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginRetailerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginRetailerFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_login_retailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login_login_button.setOnClickListener {
            val isConnectedToNetwork = isConnected(requireContext())

            if (!isConnectedToNetwork) {
                showDialog()
            }
            if (!validateFields()) {
                return@setOnClickListener
            }


            //getData

            var phoneNumber = txt_login_phone_number.editText!!.text.toString().trim()
            val password = txt_login_password.editText!!.text.toString().trim()

            if (phoneNumber[0] == '0') {
                phoneNumber = phoneNumber.substring(1)
            }

            val formattedPhoneNumber = "+${login_countrycodepicker.fullNumber}$phoneNumber"

            val checkUser =
                FirebaseDatabase.getInstance()
                    .getReference("Users")
                    .orderByChild("phoneNo")
                    .equalTo(formattedPhoneNumber)


            checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        txt_login_phone_number.error = null
                        txt_login_phone_number.isErrorEnabled = false

                        val systemPassword = snapshot
                            .child(formattedPhoneNumber)
                            .child("password").value

                        if (systemPassword!! == password) {
                            txt_login_password.error = null
                            txt_login_password.isErrorEnabled = false

                            val fullname = snapshot
                                .child(formattedPhoneNumber)
                                .child("fullname").value

                            val email = snapshot
                                .child(formattedPhoneNumber)
                                .child("email").value

                            val phoneNumber = snapshot
                                .child(formattedPhoneNumber)
                                .child("phoneNo").value

                            val dateOfBirth = snapshot
                                .child(formattedPhoneNumber)
                                .child("date").value

                            val username =
                                snapshot.child(formattedPhoneNumber).child("username").value
                            val gender = snapshot.child(formattedPhoneNumber).child("gender").value

                            SessionManager(requireContext()).createLoginSession(
                                fullname as String,
                                username as String,
                                email as String,
                                phoneNumber as String,
                                password,
                                dateOfBirth as String,
                                gender as String

                            )

                            findNavController().navigate(R.id.action_loginRetailerFragment_to_retailerDashboardActivity)







                            Toast.makeText(
                                requireContext(),
                                "Name : $fullname \n " +
                                        "Email: $email \n " +
                                        "Phone: $phoneNumber \n " +
                                        "Age: $dateOfBirth",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Password does not match",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "No such user exist", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                }

            })
        }

        btn_login_reset_password.setOnClickListener {
            findNavController()
                .navigate(R.id.action_loginRetailerFragment_to_forgetPasswordSelectionFragment)
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setMessage("Please connect to the internet to proceed further")
            .setCancelable(false)
            .setPositiveButton("Coonect", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }

            })
            .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    findNavController().navigate(R.id.action_loginRetailerFragment_to_retailerSignUpSignInFragment)
                }

            })

        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }


        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }

    }

    private fun validateFields(): Boolean {
        val phoneNumber = txt_login_phone_number.editText!!.text.toString().trim()
        val password = txt_login_password.editText!!.text.toString().trim()

        if (phoneNumber.isEmpty()) {
            txt_login_phone_number.error = "Phone number cannot be empty"
            txt_login_phone_number.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            txt_login_password.error = "Password cannot be empty"
            txt_login_password.requestFocus()
            return false
        }

        return true
    }


}
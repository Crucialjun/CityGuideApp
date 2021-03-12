package com.example.cityguideapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_verify_otp.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [VerifyOtpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerifyOtpFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    var codeBySystem = ""
    var phoneNumber = ""
    var fullName = ""
    var username = ""
    var email = ""
    var password = ""
    var gender = ""
    var date = ""
    private val args: VerifyOtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_verify_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneNumber = args.phoneNumber
        fullName = args.fullName
        username = args.userName
        email = args.email
        password = args.password
        gender = args.gender
        date = args.date

        sendVerificationToUser(phoneNumber)

        btn_verify_code.setOnClickListener {
            if (verify_otp_pin_view.text!!.isNotEmpty()) {
                verifyCode(verify_otp_pin_view.text.toString())
            }
        }
    }

    private fun sendVerificationToUser(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            val code = p0.smsCode
            if (code != null) {
                verify_otp_pin_view.setText(code)
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("TAG", "onVerificationFailed: ${p0.message} ")
            Toast.makeText(context, "${p0.message}", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            codeBySystem = p0

        }
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(codeBySystem, code)
        signInUsingCredentials(credential)
    }

    private fun signInUsingCredentials(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(context, "Verification Successful", Toast.LENGTH_SHORT).show()

                    val user = task.result?.user

                    storeUserData()
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Verification Not Completed! Please Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }

    private fun storeUserData() {
        val rootNode = FirebaseDatabase.getInstance()
        val ref = rootNode.getReference("Users")

        val newUser = UserHelper(fullName, username, email, phoneNumber, password, date, gender)

        ref.child(phoneNumber).setValue(newUser)
    }
}
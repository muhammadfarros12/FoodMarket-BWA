package com.farroos.foodmarket.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.request.RegisterRequest
import com.farroos.foodmarket.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    var filePath: Uri? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()
    }

    private fun initListener() {
        img_profile.setOnClickListener {
            ImagePicker.with(this)
                    .cameraOnly()
                    .start()

            btn_continue.setOnClickListener {

                var fullname = edt_full_name.text.toString()
                var email = edt_email.text.toString()
                var pass = edt_password.text.toString()

                if (fullname.isNullOrEmpty()) {
                    edt_full_name.error = "Silahkan masukkan fullname"
                    edt_full_name.requestFocus()
                } else if (email.isNullOrEmpty()) {
                    edt_email.error = "Silahkan masukkan fullname"
                    edt_email.requestFocus()
                } else if (pass.isNullOrEmpty()) {
                    edt_password.error = "Silahkan masukkan fullname"
                    edt_password.requestFocus()
                } else {
                    var data = RegisterRequest(
                            fullname,
                            email,
                            pass,
                            pass,
                            "", "", "", "",
                            filePath
                    )

                    var bundle = Bundle()
                    bundle.putParcelable("data", data)

                    Navigation.findNavController(it)
                            .navigate(R.id.action_fragmentSignUp_to_fragmentSignUpAddress, bundle)

                    (activity as AuthActivity).toolbarSignUpAddress()
                }
            }
        }
    }

    private fun initDummy() {
        /*edt_full_name.setText("Testing User")
        edt_email.setText("testing@mail.com")
        edt_password.setText("qwertyuiop")*/
        edt_full_name.setText("Jennie Kim")
        edt_email.setText("jennie.kim@blackpink.co")
        edt_password.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img_profile)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}
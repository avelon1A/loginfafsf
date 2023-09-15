package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.login.AuthViewModelFactory
import com.example.login.LoginActivity
import com.example.login.model.AuthViewModel


class Homefragement : Fragment() {

        private lateinit var imageView: ImageView
        private lateinit var button: Button
        private lateinit var logout:Button
    private lateinit var viewModel: AuthViewModel


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_homefragement, container, false)

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            imageView = view.findViewById(R.id.selected_image)
            button = view.findViewById(R.id.pic_Btn)

            button.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 0)
            }
            viewModel = ViewModelProvider(requireActivity(), AuthViewModelFactory(requireContext()))
                .get(AuthViewModel::class.java)

            logout = view.findViewById(R.id.logout)

            logout.setOnClickListener {
                viewModel.logout()


                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                activity?.finish()
            }



        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
                // Proceed and display the image in the ImageView
                val uri = data.data

                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)

                imageView.setImageBitmap(bitmap)
            }
        }
    }

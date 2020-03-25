package com.example.caseapppharmy.ui.fragments.profile_fragment

import android.Manifest.permission.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.caseapppharmy.R
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import com.example.caseapppharmy.ui.authentication.GeneralViewModel
import com.example.caseapppharmy.util.SharedPrefUtils
import com.example.caseapppharmy.util.Utilities
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel: GeneralViewModel
    private var imageUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillUserInfo()

        saveUserInfo.setOnClickListener {
            saveUserInfo()
        }

        editProfileImage.setOnClickListener {
            getPermissions()
        }
    }

    private fun saveUserInfo() {
        Utilities.validateFields(userName, userLastName, userMail)
        val username = userName.text.toString()
        val userlastname = userLastName.text.toString()
        val usermail = userMail.text.toString()
        val userPassword = userPassword.text.toString()
        val userPhoto = imageUrl

        viewModel.update(
            UsersData(
                usermail, userPassword, username, userlastname, userPhoto
            )
        )
        Toast.makeText(context, "Değişiklikler başarıyla kaydedilmiştir.", Toast.LENGTH_SHORT).show()
    }

    private fun fillUserInfo() {
        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            for (i in it) {
                if (i.userMail == context?.let { it1 -> SharedPrefUtils.getUserMail(it1) }) {
                    userName.setText(i.firstName.toString())
                    userLastName.setText(i.lastName.toString())
                    userMail.setText(i.userMail)
                    userPassword.setText(i.password.toString())
                    setProfileImage(i.photo.toString())
                }
            }
        })
    }

    private fun getPermissions() {
        if (context?.let { ContextCompat.checkSelfPermission(it, CAMERA) } == PackageManager.PERMISSION_DENIED
            && context?.let { ContextCompat.checkSelfPermission(it, WRITE_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_DENIED
            && context?.let { ContextCompat.checkSelfPermission(it, READ_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_DENIED) {

            val permissions = arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, PERMISSION_CODE)
        } else {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private const val PERMISSION_CODE = 1001
        private const val IMAGE_PICK_CODE = 1000
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(context, "Lütfen gerekli izinleri sağlayın.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            imageUrl = uri.toString()
            setProfileImage(imageUrl)
        }
    }

    private fun setProfileImage(uri: String) {
        if (uri.isNotEmpty())
            context?.let { Glide.with(it).load(uri).apply(RequestOptions.circleCropTransform()).into(profileImage) }

    }
}
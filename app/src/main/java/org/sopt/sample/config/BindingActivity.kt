package org.sopt.sample.config

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BindingActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }



}
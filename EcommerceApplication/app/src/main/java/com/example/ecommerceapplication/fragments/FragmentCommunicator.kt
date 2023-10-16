package com.example.ecommerceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment


interface FragmentCommunicator {
    fun communicator(instance: Fragment)
    fun DetailedCommunicator(bundle: Bundle)
}
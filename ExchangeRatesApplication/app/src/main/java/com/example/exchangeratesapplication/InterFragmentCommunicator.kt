package com.example.exchangeratesapplication

import MyData
import androidx.fragment.app.Fragment

interface InterFragmentCommunicator {
    fun communicator(instance: Fragment, data: MyData)
}
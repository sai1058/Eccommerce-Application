package com.example.ecommerceapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.viewModels.FakePaymentViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class FakePaymentFragment : Fragment() {
    private val fakePaymentViewModel: FakePaymentViewModel by viewModels()
    private lateinit var cardNumberInputLayout: TextInputLayout
    private lateinit var expiryDateInputLayout: TextInputLayout
    private lateinit var cvvInputLayout: TextInputLayout
    private lateinit var cardNumber: TextInputEditText
    private lateinit var expiryDate: TextInputEditText
    private lateinit var cvv: TextInputEditText
    private lateinit var btnPay: Button
    private lateinit var back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fake_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardNumberInputLayout = view.findViewById(R.id.cardNumberInputLayout)
        expiryDateInputLayout = view.findViewById(R.id.expiryDateInputLayout)
        cvvInputLayout = view.findViewById(R.id.cvvInputLayout)
        cardNumber = view.findViewById(R.id.cardNumber)
        expiryDate = view.findViewById(R.id.expiryDate)
        cvv = view.findViewById(R.id.enterCvv)
        btnPay = view.findViewById(R.id.paymentBtn)
        back = view.findViewById(R.id.backArrowForPayment)

        back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        cardNumber.addTextChangedListener {
            cardNumberInputLayout.error = null
            updatePayNowButtonStateChange()
        }
        expiryDate.addTextChangedListener {
            expiryDateInputLayout.error = null
            updatePayNowButtonStateChange()
        }
        cvv.addTextChangedListener {
            cvvInputLayout.error = null
            updatePayNowButtonStateChange()
        }

    }

    fun enablePaynowButton(cardNumber: String, expiryDate: String, cvvNum: String): Boolean {
        cardNumberInputLayout.error = null
        expiryDateInputLayout.error = null
        cvvInputLayout.error = null
        var x = false
        if (fakePaymentViewModel.isValid(cardNumber, expiryDate, cvvNum)) {
            x = true
        } else {
            if (!fakePaymentViewModel.isCardNumberIsValid(cardNumber)) {
                cardNumberInputLayout.error = "Enter Valid Card Number"
            }
            else{
                cardNumberInputLayout.error = null
            }
            if (!fakePaymentViewModel.isExpiryDateIsValid(expiryDate)) {
                expiryDateInputLayout.error = "Enter valid Expiry Date"
            }
            else{
                expiryDateInputLayout.error = null
            }
            if (!fakePaymentViewModel.isCvvNumberIsValid(cvvNum)) {
                cvvInputLayout.error = "Invalid CvvNum"
            }
            else{
                cvvInputLayout.error = null
            }
        }
        return x
    }

    private fun updatePayNowButtonStateChange() {
        val card = cardNumber.text.toString()
        val date = expiryDate.text.toString()
        val cvvNum = cvv.text.toString()
        btnPay.isEnabled = false
        if (enablePaynowButton(card, date, cvvNum)) {
            btnPay.isEnabled = true
            btnPay.setOnClickListener {
                val cartScreenViewModel = (requireActivity() as MainActivity).cartScreen
                cartScreenViewModel?.clearCart()
                (activity as FragmentCommunicator).communicator(PaymentDoneFragment.newInstance())
            }
        }
    }
    companion object {
        fun newInstance() = FakePaymentFragment()
    }
}

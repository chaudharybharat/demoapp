package com.jshealth.demoapp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.jshealth.demoapp.R
import com.jshealth.demoapp.comman.Validation
import kotlinx.android.synthetic.main.set_up_pin_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class SetUpPinFragment : Fragment(), View.OnClickListener {



    internal var edt1: EditText? = null
    internal var edt2: EditText? = null
    internal var edt3: EditText? = null
    internal var edt4: EditText? = null
    internal var tv_title: TextView? = null
    internal var PIN: String? = ""
    internal var first_edt = ""
    internal var second_edt = ""
    internal var third_edt = ""
    internal var forth_edt = ""
    internal var str_pin = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.set_up_pin_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setclickListener()
    }

    private fun setclickListener() {
       tv_1.setOnClickListener(this)
       tv_2.setOnClickListener(this)
       tv_3.setOnClickListener(this)
       tv_4.setOnClickListener(this)
        tv_5.setOnClickListener(this)
       tv_6.setOnClickListener(this)
       tv_7.setOnClickListener(this)
        tv_8.setOnClickListener(this)
        tv_9.setOnClickListener(this)
        tv_0.setOnClickListener(this)
        tv_delete.setOnClickListener(this)
    }



    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_0 -> setPinValue("0")
            R.id.tv_1 -> setPinValue("1")
            R.id.tv_2 -> setPinValue("2")
            R.id.tv_3 -> setPinValue("3")
            R.id.tv_4 -> setPinValue("4")
            R.id.tv_5 -> setPinValue("5")
            R.id.tv_6 -> setPinValue("6")
            R.id.tv_7 -> setPinValue("7")
            R.id.tv_8 -> setPinValue("8")
            R.id.tv_9 -> setPinValue("9")
            R.id.tv_delete -> removeValue()

            else -> {
            }
        }
    }

    private fun removeValue() {
        val frirst_edt = edt1!!.text.toString()
        val second_edt = edt2!!.text.toString()
        val third_edt = edt3!!.text.toString()
        val four_edt = edt4!!.text.toString()
        if (str_pin.length > 0 && PIN!!.length > 0) {
            str_pin.substring(1)
            PIN!!.substring(1)
        }
        if (Validation.isRequiredField(four_edt)) {
            edt4!!.setText("")
        } else if (Validation.isRequiredField(third_edt)) {
            edt3!!.setText("")
        } else if (Validation.isRequiredField(second_edt)) {
            edt2!!.setText("")
        } else if (Validation.isRequiredField(frirst_edt)) {
            edt1!!.setText("")
        }
    }

    fun setPinValue(number: String) {
        if (str_pin.equals("", ignoreCase = true)) {
            if (PIN != null && PIN!!.length == 4) {
                if (str_pin.equals("", ignoreCase = true)) {
                    str_pin = PIN!!
                    PIN = ""
                    tv_title!!.text = "Enter Comform PIN"
                } else {
                    tv_title!!.text = "Enter a 4 Digit PIN"
                    if (str_pin.equals(PIN!!, ignoreCase = true)) {

                        Toast.makeText(activity, "successfull ", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(activity, "Not match Pin try again", Toast.LENGTH_LONG)
                            .show()

                    }
                    PIN = ""
                    str_pin = ""
                }

                edt1!!.setBackgroundResource(R.drawable.round_shap)
                edt2!!.setBackgroundResource(R.drawable.round_shap)
                edt3!!.setBackgroundResource(R.drawable.round_shap)
                edt4!!.setBackgroundResource(R.drawable.round_shap)
                edt1!!.setText("")
                edt2!!.setText("")
                edt3!!.setText("")
                edt4!!.setText("")
            }
            first_edt = edt1!!.text.toString()
            second_edt = edt2!!.text.toString()
            third_edt = edt3!!.text.toString()
            forth_edt = edt4!!.text.toString()
            if (!Validation.isRequiredField(first_edt)) {
                edt1!!.setText(number)
                edt1!!.setBackgroundResource(R.drawable.round_full_shap)
                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(second_edt)) {
                edt2!!.setText(number)
                edt2!!.setBackgroundResource(R.drawable.round_full_shap)
                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(third_edt)) {
                edt3!!.setText(number)
                edt3!!.setBackgroundResource(R.drawable.round_full_shap)
                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(forth_edt)) {
                edt4!!.setText(number)
                edt4!!.setBackgroundResource(R.drawable.round_full_shap)

                PIN = PIN!! + number
            }
        } else {
            first_edt = edt1!!.text.toString()
            second_edt = edt2!!.text.toString()
            third_edt = edt3!!.text.toString()
            forth_edt = edt4!!.text.toString()
            if (!Validation.isRequiredField(first_edt)) {
                edt1!!.setText(number)
                edt1!!.setBackgroundResource(R.drawable.round_full_shap)

                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(second_edt)) {
                edt2!!.setText(number)
                edt2!!.setBackgroundResource(R.drawable.round_full_shap)

                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(third_edt)) {
                edt3!!.setText(number)
                edt3!!.setBackgroundResource(R.drawable.round_full_shap)
                PIN = PIN!! + number
            } else if (!Validation.isRequiredField(forth_edt)) {
                edt4!!.setText(number)
                edt4!!.setBackgroundResource(R.drawable.round_full_shap)

                PIN = PIN!! + number
            }
            if (PIN != null && PIN!!.length == 4) {
                tv_title!!.text = "Enter a 4 Digit PIN"
                if (str_pin.equals(PIN!!, ignoreCase = true)) {


                    Toast.makeText(activity, "successfull ", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "Not match Pin try again", Toast.LENGTH_LONG).show()

                }
                PIN = ""
                str_pin = ""
                edt1!!.setBackgroundResource(R.drawable.round_shap)
                edt2!!.setBackgroundResource(R.drawable.round_shap)
                edt3!!.setBackgroundResource(R.drawable.round_shap)
                edt4!!.setBackgroundResource(R.drawable.round_shap)
                edt1!!.setText("")
                edt2!!.setText("")
                edt3!!.setText("")
                edt4!!.setText("")

            }

        }


    }
}
package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class bmiActivity : AppCompatActivity() {
    private var Units = 0
    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(this.binding!!.actionBar)
        // show arrow left
        if(supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.binding!!.actionBar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportActionBar?.title = "BMI Calculator"

        setupRadio()
        binding?.btnCalculate?.setOnClickListener {
            if(Units == 0){
                this.setupMetricCalculator()
            }else{
                this.setupUSCalculator()
            }
        }
    }

    private fun setupRadio() {
        binding?.rgUnits?.setOnCheckedChangeListener { _, i ->
            if(binding?.rgMetric?.id == i) {
                Units = 0
                setupMetricXML()

            }
            else {
                Units = 1
                setupUSXML()
            }
        }
    }

    private fun setupMetricXML(){
        binding?.etPound?.text?.clear()
        binding?.etInch?.text?.clear()
        binding?.etFeet?.text?.clear()
        binding?.rlUS?.visibility = View.INVISIBLE

        binding?.etKg?.text?.clear()
        binding?.etcm?.text?.clear()
        binding?.rlMetric?.visibility = View.VISIBLE
    }

    private fun setupUSXML(){
        binding?.etKg?.text?.clear()
        binding?.etcm?.text?.clear()
        binding?.rlUS?.visibility = View.VISIBLE

        binding?.etPound?.text?.clear()
        binding?.etInch?.text?.clear()
        binding?.etFeet?.text?.clear()
        binding?.rlMetric?.visibility = View.INVISIBLE
    }

    private fun setupMetricCalculator(){
        if(binding?.etKg?.text.toString().isNotEmpty() && binding?.etcm?.text.toString().isNotEmpty()){

            // The height value is converted to float value and divided by 100 to convert it to meter.
            val heightValue: Float = binding?.etcm?.text.toString().toFloat() / 100

            // The weight value is converted to float value
            val weightValue: Float = binding?.etKg?.text.toString().toFloat()

            // BMI value is calculated in METRIC UNITS using the height and weight value.
            val bmi = weightValue / (heightValue * heightValue)
            summary(bmi)
        }else{
            Toast.makeText(this,"Please fill blank",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupUSCalculator(){
        if(binding?.etPound?.text.toString().isNotEmpty() && binding?.etFeet?.text.toString().isNotEmpty() && binding?.etInch?.text.toString().isNotEmpty()){


            val usUnitHeightValueFeet: String = binding?.etFeet?.text.toString() // Height Feet value entered in EditText component.
            val usUnitHeightValueInch: String = binding?.etInch?.text.toString() // Height Inch value entered in EditText component.
            val usUnitWeightValue: Float = binding?.etPound?.text.toString().toFloat() // Weight value entered in EditText component.

            // Here the Height Feet and Inch values are merged and multiplied by 12 for converting it to inches.
            val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

            // This is the Formula for US UNITS result.
            // Reference Link : https://www.cdc.gov/healthyweight/assessing/bmi/childrens_bmi/childrens_bmi_formula.html
            val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
            summary(bmi)
        }else{
            Toast.makeText(this,"Please fill blank",Toast.LENGTH_SHORT).show()
        }
    }

    private fun summary(bmi:Float){


        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Use to set the result layout visible
        binding?.llSummary?.visibility = View.VISIBLE

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMI?.text = bmiValue
        binding?.tvSuggestion?.text = bmiDescription
        binding?.tvSummary?.text = bmiLabel
    }
}
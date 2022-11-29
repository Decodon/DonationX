package ie.wit.donationx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ie.wit.donationx.databinding.ActivityDonateBinding

class Donate : AppCompatActivity() {

    private lateinit var donateLayout : ActivityDonateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donateLayout = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(donateLayout.root)

        donateLayout.progressBar.max = 10000

        donateLayout.amountPicker.minValue = 1
        donateLayout.amountPicker.maxValue = 1000

        donateLayout.amountPicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            donateLayout.paymentAmount.setText("$newVal")
        }

        var totalDonated = 0

        donateLayout.donateButton.setOnClickListener {
            val amount = if (donateLayout.paymentAmount.text.isNotEmpty())
                donateLayout.paymentAmount.text.toString().toInt() else donateLayout.amountPicker.value
            if(totalDonated >= donateLayout.progressBar.max)
                Toast.makeText(applicationContext,"Donate Amount Exceeded!",Toast.LENGTH_LONG).show()
            else {
                totalDonated += amount
                donateLayout.totalSoFar.text = getString(R.string.totalSoFar,totalDonated)
                donateLayout.progressBar.progress = totalDonated
            }
        }
    }
}
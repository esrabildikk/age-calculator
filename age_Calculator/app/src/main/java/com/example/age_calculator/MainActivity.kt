package com.example.age_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.age_calculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun clickDataPicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day =myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            binding.textViewSelectedDate.text = selectedDate


            //seçilen tarihi veri şeklinde kullanabilmek için:
            //simpleDataFormat sınıfı, tarihleri belirli bir desene göre biçimlendirmek için kullanılır.
            //
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            //sdf.parse() metodu,verilen Stringi belirtilen desene göre bir Date nesnesine dönüştürür.
            val theDate = sdf.parse(selectedDate)
            //bu işlemden sonra 'theDate' değişkeni, istediğimiz diğer işlemler için kullanılabilir.

            //Date nesnesinin time özelliği kullanılarak seçilen tarihin milisaniye cinsinden zaman değeri elde edilir.
            //1 dakikaya bölünerek (60000) tarihin başından itibaren geçen dakika sayısı hesaplanır
            val selectedDateInMinutes = theDate?.time?.div(60000)

            // System.currentTimeMillis() ile geçerli zamana ait milisaniye değeri elde edilir.
            //sdf.format metodu ile belirlenen desene göre bir String dönüştürülür.
            //daha sonra sdf.parse metodu ile bu string tekrar Date nesnesine dönüştürülür.
            //güncel tarih currentDate değişkenine atanır.
          val currentDate = Date()
            val currentFormattedDate = sdf.parse(sdf.format(currentDate))

            //guncel tarih dakika cinsinden değeri hesaplanır.
            val currentDateInMinutes = currentFormattedDate?.time?.div(60000)
            //secilen tarih(selectedDateInMinutes) ile guncel tarih (currentDateInMinutes) arasındaki
            //fark hesaplanır. geçmişe göre dakika cinsinden ne kadar süre önce olduğunu temsil eder.
            val differenceInMinutes = currentDateInMinutes?.minus(selectedDateInMinutes!!)

            binding.textViewMinutesDate.text = differenceInMinutes.toString()

               },
        year,
        month,
        day).show()
    }
}
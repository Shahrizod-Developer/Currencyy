package uz.android.pulbirliklari

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.dialog_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.android.pulbirliklari.adapters.RecyclerAdapter
import uz.android.pulbirliklari.databinding.ActivityMainBinding
import uz.android.pulbirliklari.networking.RetrofitInstance

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitInstance.api

        api.getAllCurrency().enqueue(object : Callback<List<Currencyy>> {
            override fun onResponse(
                    call: Call<List<Currencyy>>,
                    response: Response<List<Currencyy>>
            ) {
                if (response.isSuccessful){
                    val list: List<Currencyy> =  response.body() as List<Currencyy>
                    binding.rv.adapter = RecyclerAdapter(list){
                        showDiaolg(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<Currencyy>>, t: Throwable) {
                Toast.makeText(binding.root.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun showDiaolg(currency: Currencyy) {

        val dialog = AlertDialog.Builder(this@MainActivity).create()
        val dialogView = layoutInflater.inflate(R.layout.dialog_view, binding.root, false)

        var ss = dialogView.findViewById<TextView>(R.id.nomi)
        ss.text = currency.CcyNm_UZ

        dialog.setView(dialogView.rootView)

        dialogView.hisobla.setOnClickListener{
            val summa = dialogView.et_enter.text.toString()
            if(summa.isNotEmpty())
            {
                val natija: Double = summa.toDouble() * currency.Rate.toDouble()
                dialogView.result.text = natija.toString()
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogView)
        dialog.show()

    }
}
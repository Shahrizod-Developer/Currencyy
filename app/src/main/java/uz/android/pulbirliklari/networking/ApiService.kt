package uz.android.pulbirliklari.networking

import retrofit2.Call
import retrofit2.http.GET
import uz.android.pulbirliklari.model.Currencyy

interface ApiService {

    @GET("arkhiv-kursov-valyut/json/")
    fun getAllCurrency(): Call<List<Currencyy>>
}
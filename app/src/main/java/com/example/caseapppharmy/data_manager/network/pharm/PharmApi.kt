package com.example.caseapppharmy.data_manager.network.pharm

import com.example.caseapppharmy.data_manager.modals.Pharmacy
import retrofit2.Call
import retrofit2.http.*

interface PharmApi {


    @GET("health/dutyPharmacy")
    fun getPharmacyList(
        @Query("ilce") district: String,
        @Query("il") city: String
    ): Call<Pharmacy>


}
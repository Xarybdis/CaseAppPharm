package com.example.caseapppharmy.ui.fragments.pharmacy_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caseapppharmy.R
import com.example.caseapppharmy.data_manager.modals.Result
import com.example.caseapppharmy.ui.authentication.GeneralViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy.*

class PharmacyListFragment : Fragment() {

    private lateinit var adapter: PharmacyAdapter
    private lateinit var viewModel: GeneralViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPharmacyList("", "Ankara")

        searchPharmacy.setOnClickListener {
            val city = filterCity.text.toString()
            val district = filterDistrict.text.toString()
            if (city.isNotEmpty() && district.isNotEmpty()) {
                setPharmacyList(district, city)
            } else {
                Toast.makeText(context, "Lütfen şehir ve ilçe bilgisini giriniz.", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun setPharmacyList(district: String, city: String) {
        val list = mutableListOf<Result>()

        viewModel.fetchPharmacyList(district, city).observe(viewLifecycleOwner, Observer {
            it.forEach { it ->
                list.add(Result(it.address, it.dist, it.loc, it.name, it.phone))
                adapter.notifyDataSetChanged()
            }

            pharmacyRecycler.isVisible = list.isNotEmpty()
            emptyHolder.isVisible = list.isEmpty()
        })

        adapter = PharmacyAdapter(list)
        pharmacyRecycler?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        pharmacyRecycler?.setHasFixedSize(true)
        pharmacyRecycler?.adapter = adapter
    }
}
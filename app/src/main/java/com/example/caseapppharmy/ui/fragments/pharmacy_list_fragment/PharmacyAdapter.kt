package com.example.caseapppharmy.ui.fragments.pharmacy_list_fragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.caseapppharmy.R
import com.example.caseapppharmy.data_manager.modals.Result
import kotlinx.android.synthetic.main.list_item_pharmacy.view.*


class PharmacyAdapter(private val list: List<Result>) :
    RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>() {
    var mExpandedPosition = -1
    var mPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyAdapter.PharmacyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pharmacy, parent, false)
        return PharmacyViewHolder(itemview)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PharmacyAdapter.PharmacyViewHolder, position: Int) {
        holder.bind(list[position], position)
    }


    inner class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pharmacyItem: Result, position: Int) {

            val isExpanded = position == mExpandedPosition
            itemView.pharmDetailExpanded.isVisible = isExpanded

            itemView.pharmacyDetail.setOnClickListener {
                if (isExpanded) {
                    mExpandedPosition = -1
                } else {
                    mExpandedPosition = position
                }
                notifyItemChanged(position)
            }
            itemView.pinMap.setOnClickListener {
                pharmacyItem.address?.let { it1 -> proceedToMaps(it1) }
            }
            itemView.pharmacyPhone.setOnClickListener {
                pharmacyItem.phone?.let { it1 -> proceedToDial(it1) }
            }
            itemView.pharmacyAddress.text = "${pharmacyItem.address} ${pharmacyItem.dist}"
            itemView.phoneNumber.text = pharmacyItem.phone
            itemView.pharmacyName.text = pharmacyItem.name
        }

        private fun proceedToMaps(address: String) {
            val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            itemView.context.startActivity(mapIntent)
        }

        private fun proceedToDial(phoneNumber: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("tel:$phoneNumber")
            itemView.context.startActivity(intent)
        }
    }
}
package com.stech.nagwaassignment.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stech.nagwaassignment.R
import com.stech.nagwaassignment.common.DOWNLOAD
import com.stech.nagwaassignment.common.DOWNLOODING
import com.stech.nagwaassignment.common.NOTHINGSTATUES
import com.stech.nagwaassignment.common.PINNED
import com.stech.nagwaassignment.databinding.AdapterAttatchedBinding
import com.stech.nagwaassignment.entities.AttachedFiles
import com.stech.nagwaassignment.interfaces.ClickRecycler


class AttachedAdapter (var context: Context, var results: ArrayList<AttachedFiles>,var clicedItem:ClickRecycler) :
RecyclerView.Adapter<AttachedAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.getContext())
        val binding :AdapterAttatchedBinding=
            DataBindingUtil.inflate(layoutInflater, R.layout.adapter_attatched, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result=results[position]
        holder.binding.result=result
        when(result.statues){
            NOTHINGSTATUES->{
                Glide.with(context).load(R.drawable.ic_download).into(holder.binding.download)
            }
            PINNED->{
                Glide.with(context).load(R.drawable.ic_time_left).into(holder.binding.download)

            }
            DOWNLOAD->{
                Glide.with(context).load(R.drawable.ic_check).into(holder.binding.download)

            }
            DOWNLOODING->{
               holder.binding.download.visibility=GONE

            }

        }


        holder.binding.download.setOnClickListener {
            if (result.statues.equals(NOTHINGSTATUES)) {
                result.statues= DOWNLOODING
                holder.binding.result=result

                val timer = object : CountDownTimer(10000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {

                       holder.binding.progressCircular.setProgress(10000/100-millisUntilFinished.toInt()/100)
                        holder.binding.text.text=(10000/100-millisUntilFinished.toInt()/100).toString()+"%"
                        Log.e("CountDownTimer",(10000/100-millisUntilFinished.toInt()/100).toString())
                    }

                    override fun onFinish() {
                        holder.binding.progressCircular.setProgress(100)
                        holder.binding.text.text="100%"

                        result.statues= DOWNLOAD
                        holder.binding.result=result
                    }
                }
                timer.start()
                clicedItem.ClicedItem(position)
            }
        }



    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class ViewHolder( binding: AdapterAttatchedBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        var binding: AdapterAttatchedBinding = binding
    }






}
package com.stech.nagwaassignment.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stech.nagwaassignment.app.ApplicationApp
import com.stech.nagwaassignment.common.NOTHINGSTATUES

import com.stech.nagwaassignment.databinding.ActivityMainBinding
import com.stech.nagwaassignment.entities.AttachedFiles
import com.stech.nagwaassignment.interfaces.ClickRecycler
import com.stech.nagwaassignment.presentation.adapters.AttachedAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity(), ClickRecycler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter :AttachedAdapter
    private lateinit var attachedArrayList: ArrayList<AttachedFiles>
    val viewModel:MainViewModel by viewModels {
        MainViewModel.AttachedViewModelFactory(
            ((application) as ApplicationApp).getAttachedUseCase,
            ((application) as ApplicationApp).attachedFileMapper
        )
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        viewModel.getAttched()

        attachedArrayList= ArrayList()
        for (i in 0..10){

            attachedArrayList.add(AttachedFiles(i.toString(),i.toString(),"VIDEO","dasdas", NOTHINGSTATUES))
        }
        binding.listItem.layoutManager=LinearLayoutManager(this)
        adapter= AttachedAdapter(this,attachedArrayList,this)
        binding.listItem.adapter=adapter
        viewModel.attachedFile.observe(this, {
            attachedArrayList.clear()
            attachedArrayList.addAll(it)
            adapter.notifyDataSetChanged()

        })
         viewModel.loading.observe(this, {
             if (it)
           binding.animationView.visibility=View.VISIBLE
             else
                 binding.animationView.visibility=View.GONE


             binding.swipeRefreshLayout.isRefreshing=false

         })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAttched()
        }

    }

    override fun ClicedItem(i: Int) {

    }


}
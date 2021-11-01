package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessBinding
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OnProcessFragment : Fragment(R.layout.fragment_on_process) {
    private lateinit var binding: FragmentOnProcessBinding
    private lateinit var adapter: OnProcessAdapter
    private val viewModel: OnProcessViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnProcessBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()


    }

    private fun handleAdapter() {
        adapter = OnProcessAdapter()
        binding.rvListOrder.adapter = adapter
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED){


               viewModel.vmGetWaitingList().collectLatest { res ->

                   when(res.status){
                       Status.LOADING ->{

                       }
                       Status.SUCCESS ->{
                           adapter.differ.submitList(res.data?.success)
                           viewModel.setTotalWaitingList(adapter.itemCount)
                       }
                   }



                   adapter.setOnItemClickListener {
                       findNavController().navigate(R.id.action_processOrderFragment_to_detailOnProcessFragment)
                   }


               }

               viewModel.totalWaitingList.collectLatest {total ->
                   binding.tvNumberOfOrder.text = total.toString()
               }


           }
        }
    }


}
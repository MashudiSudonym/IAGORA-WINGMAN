package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListBinding
import com.ssd.iagorawingman.ui.process_order.ProcessOrderContainerFragmentDirections
import com.ssd.iagorawingman.ui.process_order.waiting_list.WaitingListAdapter
import com.ssd.iagorawingman.utils.Other.collectWhenStarted
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConfirmedFragment : Fragment(R.layout.fragment_on_process_waiting_list) {

    private var _binding: FragmentOnProcessWaitingListBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListBinding
    private lateinit var adapter: WaitingListAdapter
    private val viewModel: ConfirmedViewModel by sharedViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
        setupDestinationToDetail()
    }


    private fun handleAdapter() {
        adapter = WaitingListAdapter()
        binding.rvListOrder.adapter = adapter
    }

    private fun setupDestinationToDetail() {
        adapter.setOnItemClickListener { idTransaction ->
            findNavController().navigate(
                ProcessOrderContainerFragmentDirections.moveToDetailConfirmed(idTransaction)
            )
        }
    }


    private fun subscribeToViewModel() {
        viewModel.vmGetConfirmedList.collectWhenStarted(this) { res ->
            when (res.status) {
                Status.LOADING -> {
                    Log.e("LOADING", "CONFIRMED")
                }
                Status.SUCCESS -> {
                    adapter.differ.submitList(res.data?.success)
                    Log.e("SUCCESS", "CONFIRMED")
                }

                        Status.ERROR -> {
                            Log.e("ERROR", "CONFIRMED")
                        }
                    }

                }

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
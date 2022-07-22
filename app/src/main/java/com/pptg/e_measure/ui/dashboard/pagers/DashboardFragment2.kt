package com.pptg.e_measure.ui.dashboard.pagers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.R
import com.pptg.e_measure.databinding.FragmentDashboard2Binding
import com.pptg.e_measure.databinding.FragmentDashboardBinding
import com.pptg.e_measure.ui.dashboard.DashboardAdapter
import com.pptg.e_measure.ui.dashboard.DashboardViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val model: DashboardViewModel by activityViewModels()
    private var _binding: FragmentDashboard2Binding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboard2Binding.inflate(inflater, container, false)

        adapter = DashboardAdapter(model.mList2)
        binding.rvDashboard2.let {
            it.layoutManager = LinearLayoutManager(EMApplication.context)
            it.adapter = adapter
        }

        // Inflate the layout for this fragment
        val root: View = binding.root
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        private const val TAG = "DashboardFragment2"
    }
}
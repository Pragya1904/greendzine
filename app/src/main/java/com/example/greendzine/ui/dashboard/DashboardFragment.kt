package com.example.greendzine.ui.dashboard

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greendzine.CustomAdapter
import com.example.greendzine.ModelEmp
import com.example.greendzine.R
import com.example.greendzine.databinding.FragmentDashboardBinding
import java.io.IOException
import java.io.InputStream
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.nio.channels.FileChannel.open

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val recyclerView=root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val empdata=readJson(requireContext()) //todo: read json 1and convert data to array
        val searchView=root.findViewById<SearchView>(R.id.SearchView)



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               filterList(newText)
                 return true
            }
        })

        val adapter=CustomAdapter(empdata)
        recyclerView.adapter = adapter

        return root
    }

    private fun filterList(query: String?) {
        val empdata=readJson(requireContext())
        val  filteredList = ArrayList<ModelEmp>();
        if (!query.isNullOrBlank()) {
            for (item in empdata) {

                if (item.name.contains(query, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
        }
        else {
            // If the query is null or empty, show the entire list
            filteredList.addAll(empdata)
        }
        val adapter=CustomAdapter(filteredList)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun readJson(context: Context): ArrayList<ModelEmp> {
    val modelEmpList = ArrayList<ModelEmp>()
    try {
        val inputStream: InputStream = context.assets.open("empData.json")
        val json = inputStream.bufferedReader().use { it.readText() }

        val type = object : TypeToken<ArrayList<ModelEmp>>() {}.type
        modelEmpList.addAll(Gson().fromJson(json, type))

    } catch (e: IOException) {
        // Handle the exception, e.g., log it
        e.printStackTrace()
    }
    return modelEmpList
}

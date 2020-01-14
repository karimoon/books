package com.karim.booksapp.ui.search


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karim.booksapp.*
import com.karim.booksapp.ui.MainActivity

import com.karim.booksapp.ui.main.MainViewModel
import com.karim.booksapp.utilities.SpUtil
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var navController: NavController

//    @Inject
//    lateinit var providerFactory : ViewModelProviderFactory


    override fun onResume() {
        super.onResume()
        ( activity as AppCompatActivity).supportActionBar?.hide()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)


        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        viewModel=ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)


        if(!viewModel.isNetworkAvailable()){
            viewModel.displayBooksFromCache()
            navController.navigate(R.id.action_searchFragment_to_bookListFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener {
                view ->
            if(!searchEditText.text.isNullOrBlank()){
                searchBooks(searchEditText.text.toString())
            }
        }

        advancedTextView.setOnClickListener({
                view ->  avdancedSearch()
        })
    }

    private fun avdancedSearch() {
        navController.navigate(R.id.action_searchFragment_to_advancedSearchFragment)
    }

    fun searchBooks(query : String){
        val data = viewModel.configSearchWithDefaultParams(query)

        viewModel.queryMapSelected.value = data

        SpUtil.setPreferenceString(requireContext(), "searchValue", query)

        navController.navigate(R.id.action_searchFragment_to_bookListFragment)

    }


}

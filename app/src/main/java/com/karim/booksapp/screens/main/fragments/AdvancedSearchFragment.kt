package com.karim.booksapp.ui.search


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karim.booksapp.*

import com.karim.booksapp.screens.main.viewmodels.MainViewModel
import com.karim.booksapp.utils.SpUtil
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_advanced_search.*
import javax.inject.Inject

class AdvancedSearchFragment : DaggerFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefsEditor : SharedPreferences.Editor


    override fun onResume() {
        super.onResume()
        ( activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_advanced_search, container, false)

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        viewModel=ViewModelProviders.of(requireActivity(),providerFactory).get(MainViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch.setOnClickListener { view ->
            run {
                val title = etTitle.text.toString().trim { it <= ' ' }
                val author = etAuthor.text.toString().trim { it <= ' ' }
                val publisher = etPublisher.text.toString().trim { it <= ' ' }
                val isbn = etISBN.text.toString().trim { it <= ' ' }

                if (title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()) {
                    val message = getString(R.string.no_search_data)
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
                }
                else{

                    searchBooks(title, author, publisher , isbn)
                }
            }
        }
    }


    fun searchBooks(title: String, author : String,publisher: String, isbn : String){

        val data = viewModel.configSearchWithDefaultParams(title, author, publisher , isbn)

        viewModel.queryMapSelected.value = data

        data["q"]?.let { SpUtil.setPreferenceString(sharedPrefsEditor, "searchValue", it) }

        navController.navigate(R.id.action_advancedSearchFragment_to_bookListFragment)

    }


}

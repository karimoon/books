package com.karim.booksapp.ui.search


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karim.booksapp.*
import com.karim.booksapp.ui.MainActivity

import com.karim.booksapp.ui.main.MainViewModel


class AdvancedSearchFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var etPublisher: EditText
    private lateinit var etIsbn: EditText
    private lateinit var button : Button
    private lateinit var navController: NavController


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

        etTitle = view.findViewById(R.id.etTitle)
        etAuthor = view.findViewById(R.id.etAuthor)
        etPublisher = view.findViewById(R.id.etPublisher)
        etIsbn = view.findViewById(R.id.etISBN)
        button = view.findViewById(R.id.btnSearch)

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        //viewModel=(activity as MainActivity).viewModel

        viewModel=ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        button.setOnClickListener { view ->
            run {
                val title = etTitle.text.toString().trim { it <= ' ' }
                val author = etAuthor.text.toString().trim { it <= ' ' }
                val publisher = etPublisher.text.toString().trim { it <= ' ' }
                val isbn = etIsbn.text.toString().trim { it <= ' ' }

                if (title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()) {
                    val message = getString(R.string.no_search_data)
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
                }
                else{

                }
            }
        }

        return view
    }

    fun searchBooks(query : String){

        val data = viewModel.configSearchWithDefaultParams(query)
        viewModel.queryMapSelected.value = data
        navController.navigate(R.id.action_searchFragment_to_bookListFragment)

    }


}

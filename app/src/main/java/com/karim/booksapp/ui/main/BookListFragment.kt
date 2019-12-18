package com.karim.booksapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.karim.booksapp.EXTRA_SORT_BY
import com.karim.booksapp.LOG_TAG
import com.karim.booksapp.MAX_RESULTS

import com.karim.booksapp.R
import com.karim.booksapp.data.models.Book
import com.karim.booksapp.ui.MainActivity
import com.karim.booksapp.ui.detail.DetailBookActivity
import com.karim.booksapp.utilities.SpUtil

class BookListFragment : Fragment() , BooksListRecyclerAdapter.BookItemListener ,  SearchView.OnQueryTextListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var mSearchView: SearchView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController
    private var counterPage = 0
    private var mSortBy = "relevance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onResume() {
        super.onResume()
        ( activity as AppCompatActivity).supportActionBar?.show()

        if(mSortBy == "favorites"){
            viewModel.getFavoriteBooks()
        }

    }

    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        recyclerView = view.findViewById(R.id.rv_books)


        swipeLayout = view.findViewById(R.id.swipeLayout)

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )
        if (savedInstanceState != null){
            mSortBy = savedInstanceState.getString(EXTRA_SORT_BY).toString()
        }

        swipeLayout.isRefreshing=true

        initRecyclerView()

        swipeLayout.setOnRefreshListener {

            if(mSortBy == "favorites"){
                viewModel.getFavoriteBooks()
            }else{

                if(viewModel.isNetworkAvailable()){
                    viewModel.refreshData(mSortBy)
                }
                else{
                    swipeLayout.isRefreshing=false
                    (activity as MainActivity).showNetworkFailureToast()
                }
            }
        }

        viewModel=(activity as MainActivity).viewModel

            viewModel.favoriteBooksData.observe(this , Observer {
                val booksListRecyclerAdapter = BooksListRecyclerAdapter(requireContext(), it , this )
                recyclerView.adapter = booksListRecyclerAdapter
                swipeLayout.isRefreshing=false
            })


            viewModel.queryMapSelected.observe(this, Observer {
                if(it["startIndex"]==null){
                    viewModel.searchBooksWithQuery(it)
                }
            })

            viewModel.bookData.observe(viewLifecycleOwner, Observer {

                if(it.size > MAX_RESULTS){
                    recyclerView.adapter?.notifyItemRangeChanged(0, recyclerView.adapter!!.getItemCount())
                    if (recyclerView.adapter == null) {
                        recyclerView.adapter = recyclerView.adapter?:BooksListRecyclerAdapter(requireContext(), it , this )                }
                }
                else{
                    val booksListRecyclerAdapter = BooksListRecyclerAdapter(requireContext(), it , this )
                    recyclerView.adapter = booksListRecyclerAdapter
                }
                swipeLayout.isRefreshing=false
            })

        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SORT_BY, mSortBy)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        activity!!.getMenuInflater().inflate(R.menu.books_list_menu, menu)
        val  searchItem= menu?.findItem(R.id.action_search) as MenuItem
        mSearchView = MenuItemCompat.getActionView(searchItem) as SearchView
        mSearchView.setOnQueryTextListener(this)

        when (mSortBy){
            "relevance" -> menu.findItem(R.id.sort_by_most_popular).isChecked = true
            "newest" -> menu.findItem(R.id.sort_by_newest).isChecked = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_advanced_search -> {
                navController.navigate(R.id.action_bookListFragment_to_advancedSearchFragment)
            }
            R.id.sort_by_most_popular -> {
                if(!item.isChecked){

                    item.isChecked = true
                    mSortBy = "relevance"
                    swipeLayout.isRefreshing=true
                    viewModel.refreshData(mSortBy)
                }
            }
            R.id.sort_by_newest -> {

                if(!item.isChecked){

                    item.isChecked = true
                    mSortBy = "newest"
                    swipeLayout.isRefreshing=true
                    viewModel.refreshData(mSortBy)
                }
            }
            R.id.sort_by_favorites -> {
                if(!item.isChecked){

                    item.isChecked = true
                    mSortBy = "favorites"
                    swipeLayout.isRefreshing=true
                    viewModel.getFavoriteBooks()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        swipeLayout.isRefreshing=true
        recyclerView.adapter?.let {
            (it as BooksListRecyclerAdapter).clear()
        }

        val data = query?.let {
            SpUtil.setPreferenceString(requireContext(), "searchValue", it)
            viewModel.configSearchWithDefaultParams(it)

        }
        viewModel.queryMapSelected.value = data
        data?.let { viewModel.searchBooksWithQuery(it) }
        mSearchView.clearFocus()

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun initRecyclerView() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && viewModel.isNetworkAvailable() && mSortBy != "favorites") {

                    val query = viewModel.queryMapSelected.value
                    counterPage = counterPage + MAX_RESULTS
                    query?.set("maxResults", MAX_RESULTS.toString())
                    query?.set("startIndex", counterPage.toString())
                    query?.let {
                        swipeLayout.isRefreshing=true
                        viewModel.searchBooksWithQueryWithPagination(it) }
                }
            }
        })
    }


    override fun onBookItemClick(book: Book) {
        val intent = Intent(activity , DetailBookActivity::class.java)
        intent.putExtra("Book" , book)
        startActivity(intent)
    }

}

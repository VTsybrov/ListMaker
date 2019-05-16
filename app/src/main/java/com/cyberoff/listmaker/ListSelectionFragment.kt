package com.cyberoff.listmaker

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.TaskStackBuilder
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListSelectionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListSelectionFragment : Fragment(),ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    private var listener: OnFragmentInteractionListener? = null
    lateinit var listsRecyclerView: RecyclerView
    lateinit var listDataManager: ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun listItemClickerd(list: TaskList) {
        listener?.onListItemClicked(list)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

    fun addList(list: TaskList){
        listDataManager.saveList(list)
        val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
        recyclerAdapter.addList(list)

    }

    fun saveList(list: TaskList){
        listDataManager.saveList(list)
        updateLists()
    }

    fun updateLists(){
        val lists = listDataManager.readLists()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String): ListSelectionFragment{
            val fragment = ListSelectionFragment()
            return fragment

            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lists = listDataManager.readLists()
        view?.let {
            listsRecyclerView = it.findViewById<RecyclerView>(R.id.lists_recyclerview)
            listsRecyclerView.layoutManager = LinearLayoutManager(activity)
            listsRecyclerView.adapter = com.cyberoff.listmaker.ListSelectionRecyclerViewAdapter(lists, this)
        }
    }
}

package com.example.android.colourmemory.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.android.colourmemory.R
import kotlinx.android.synthetic.main.main_fragment.view.*

/**
 * 1. Randomize the ListImages[16]
 *    Init sparse boolean array[16]
 * 2. Start Try card game
 *  2.a Prepare List[2] stack
 *  2.b Push the number picked user
 *  2.c If stack size == 2, then Evaluate
 *  2.d If stack size == 1, then 2.b
 * 3. Evaluate:
 *  3.a If (the two pairs match) then
 *    3.a.i  Update to Sparse Boolean [16]
 *    3.a.ii Update score
 *    3.a.iii Make the two cards Invisible
 *  3.b If (the two pairs DONT match) then
 *    3.b.i Update score
 *    3.b.ii Reverse the card
 *    3.b.iii Go to Try 2.a
 * 4. Once all images match, show dialog to get Name and write to db
 * 5. Click on High Scores BUTTON, shows the [Rank, Name, Scores] sorted
 */

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val adapter = CardsAdapter(listOf(
        resources.getIdentifier("colour1", "drawable", activity!!.packageName),
        resources.getIdentifier("colour2", "drawable", activity!!.packageName),
        resources.getIdentifier("colour3", "drawable", activity!!.packageName),
        resources.getIdentifier("colour4", "drawable", activity!!.packageName),
        resources.getIdentifier("colour5", "drawable", activity!!.packageName),
        resources.getIdentifier("colour6", "drawable", activity!!.packageName),
        resources.getIdentifier("colour7", "drawable", activity!!.packageName),
        resources.getIdentifier("colour8", "drawable", activity!!.packageName),
        resources.getIdentifier("colour1", "drawable", activity!!.packageName),
        resources.getIdentifier("colour2", "drawable", activity!!.packageName),
        resources.getIdentifier("colour3", "drawable", activity!!.packageName),
        resources.getIdentifier("colour4", "drawable", activity!!.packageName),
        resources.getIdentifier("colour5", "drawable", activity!!.packageName),
        resources.getIdentifier("colour6", "drawable", activity!!.packageName),
        resources.getIdentifier("colour7", "drawable", activity!!.packageName),
        resources.getIdentifier("colour8", "drawable", activity!!.packageName)
    ))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {
            gridView_Images.adapter = adapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}


class CardsAdapter(private val source: List<Int>) : BaseAdapter() {
        var listRandomisedImages: List<Int> = emptyList()


    fun initDataStructures() {
        listRandomisedImages =
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
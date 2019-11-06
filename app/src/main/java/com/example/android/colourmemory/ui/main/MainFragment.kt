package com.example.android.colourmemory.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.android.colourmemory.R
import kotlinx.android.synthetic.main.card_item_layout.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.util.*

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
 *
 * UI (view, framework)
 *  - initializeGame
 *  - evaluate
 *
 * case 1.
 * domain
 *  - usecase
 *    SaveScoreUseCase()
 *    FetchScoreUseCase()
 *
 * data layer (repository)
 *  - TABLE(dkfjd): id(pirmary, auto, long), name(TEXT), score(NUMBER)
 *
 *  case 2.
 *  repository
 *  - TABLE(dkfjd): id(pirmary, auto, long), name(TEXT), score(NUMBER)
 *  - save(name, score)
 *  - fetch(): List<Score>
 *
 *      data class Score(Rank, Name, Scores)
 *
 * TODO
 *  1. draw cards to gridview using adapter. (UI)
 *  2. design database table (repository)
 */

class MainFragment : Fragment(), MainContract.MainView {

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
    ), context)

    private val evaluateStack: Stack<Int> = Stack()
    private val checkCards = SparseBooleanArray(8)

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

    //
    //----------------------------------------------------------------------------------------------
    // MainContract.MainView

    /**
     * initialize game view components
     *  1. list[2] stack
     *  2. init checkStatus: SpaseBooleanArray
     *  3. ui
     */
    override fun initializeGame() {
        evaluateStack.clear()
        checkCards.clear()

        // TODO init cards view
    }

    private fun randomizeCards() {

    }

    override fun evaluateGame(first: Int, second: Int) {
        if (adapter.getItem(first) == adapter.getItem(second)) {

        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}


class CardsAdapter(private var source: List<Int> = emptyList(), private var context: Context?) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView
            ?: LayoutInflater.from(parent.context).inflate(R.layout.card_item_layout, parent, false)

        view.cardView.setImageResource(source[position])

        return view
    }

    override fun getItem(position: Int): Any {
        return source[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int = source.size

    fun update(newData: List<Int>) {
        this.source = newData
        this.notifyDataSetChanged() // research required. why?
    }
}

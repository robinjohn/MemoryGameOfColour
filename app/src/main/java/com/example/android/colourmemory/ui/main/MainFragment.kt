package com.example.android.colourmemory.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.DrawableRes
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

    private val evaluateStack: Stack<Int> = Stack()

    private val checkCards = SparseBooleanArray(8)
    private val checkss = mutableSetOf<Int>()

    private val pairRandomised = mutableListOf(Pair(0,0))


    private val cards: MutableList<Int> by lazy {
        mutableListOf(
            R.drawable.colour1,
            R.drawable.colour2,
            R.drawable.colour3,
            R.drawable.colour4,
            R.drawable.colour5,
            R.drawable.colour6,
            R.drawable.colour7,
            R.drawable.colour8,
            R.drawable.colour1,
            R.drawable.colour2,
            R.drawable.colour3,
            R.drawable.colour4,
            R.drawable.colour5,
            R.drawable.colour6,
            R.drawable.colour7,
            R.drawable.colour8
        )
    }

    private val adapter = CardsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {
            gridView_Images.adapter = adapter
            initializeGame()
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

        adapter.update(randomizeCards())
    }

    private fun randomize(): MutableSet<Int> {
        val idxs = linkedSetOf<Int>()

        var count = 16
        while (idxs.size < 16) {
            val value = Random().nextInt(16)
            if (!idxs.contains(value)) {
                idxs.add(value)
                count--
            }
            //Log.d("wlaksdf", "aasdfasdf: ${value}, size: ${idxs.size}")
        }
        return idxs
    }

    /**
     * mix cards resource with duplicates and return 16 array of drawable resource int
     */
    private fun randomizeCards(): List<Int> {

        //copy cards to source and duplicate entries to 16

        // <index, resource>
        // 1. generate a list of 16 inter random number <list1>
        // 2. generate a 16 resource array <list2>

        var randomisedIndexes: List<Int> = listOf(0)
        val shuffledCards: MutableList<Int> = mutableListOf()
        randomisedIndexes = (1..16).map { Random().nextInt(16) }

        val randomizedIx = randomize()
        randomizedIx.forEachIndexed { index, i ->
            shuffledCards.add(index,cards[i])
        }

        return shuffledCards
    }

    override fun evaluateGame(first: Int, second: Int) {
        if (adapter.getItem(first) == adapter.getItem(second)) {
            checkss.add(adapter.getItem(first) as Int)
        }
    }


    companion object {
        fun newInstance() = MainFragment()


    }
}


class CardsAdapter(@DrawableRes private var source: List<Int> = emptyList()) : BaseAdapter() {

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

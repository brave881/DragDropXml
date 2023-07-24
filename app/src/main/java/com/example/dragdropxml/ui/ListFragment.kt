package com.example.dragdropxml.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dragdropxml.R
import com.example.dragdropxml.config.DragItem
import com.example.dragdropxml.config.DragListView
import com.example.dragdropxml.config.swipe.ListSwipeHelper
import com.example.dragdropxml.config.swipe.ListSwipeItem
import com.example.dragdropxml.config.swipe.utils.SwipeDirection
import com.example.dragdropxml.databinding.ListLayoutBinding


class ListFragment : Fragment() {
    private lateinit var binding: ListLayoutBinding
    private var mItemArray: ArrayList<Pair<Long, String>>? = null
    private var mDragListView: DragListView? = null
    private val mSwipeHelper: ListSwipeHelper? = null
    private var mRefreshLayout: MySwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ListLayoutBinding.inflate(layoutInflater, container, false)
        val view = binding.root
//        mRefreshLayout = binding.swipeRefreshLayout as MySwipeRefreshLayout
        mDragListView = binding.dragListView as DragListView
        mDragListView?.recyclerView?.isVerticalScrollBarEnabled = true
        mDragListView?.setDragListListener(object : DragListView.DragListListenerAdapter() {
            override fun onItemDragStarted(position: Int) {
//                mRefreshLayout?.isEnabled = true
                //Toast.makeText(mDragListView.getContext(), "Start - position: " + position, Toast.LENGTH_SHORT).show();
            }

            override fun onItemDragEnded(fromPosition: Int, toPosition: Int) {
//                mRefreshLayout?.isEnabled = true
                if (fromPosition != toPosition) {
                    //Toast.makeText(mDragListView.getContext(), "End - position: " + toPosition, Toast.LENGTH_SHORT).show();
                }
            }
        })

        mItemArray = java.util.ArrayList()
        for (i in 0..39) {
            mItemArray?.add(Pair(i.toLong(), "Item $i"))
        }

//        mRefreshLayout?.setScrollingView(mDragListView?.recyclerView)
//        mRefreshLayout?.setColorSchemeColors(
//            ContextCompat.getColor(
//                requireContext(), R.color.app_color
//            )
//        )
//        mRefreshLayout?.setOnRefreshListener {
//            mRefreshLayout?.postDelayed(
//                { mRefreshLayout?.isRefreshing = false }, 2000
//            )
//        }

        mDragListView?.setSwipeListener(object : ListSwipeHelper.OnSwipeListenerAdapter() {
            override fun onItemSwipeStarted(item: ListSwipeItem?) {
//                mRefreshLayout?.isEnabled = false
            }

            override fun onItemSwipeEnded(
                item: ListSwipeItem?, swipedDirection: SwipeDirection?
            ) {
//                mRefreshLayout?.isEnabled = true

                // Swipe to delete on left
                if (swipedDirection == SwipeDirection.LEFT) {
                    val adapterItem = item?.tag as Pair<*, *>
                    val pos = mDragListView?.adapter?.getPositionForItem(adapterItem)
                    mDragListView?.adapter?.removeItem(pos ?: 0)
                }
            }
        })
        setupListRecyclerView()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_disable_drag).isVisible = mDragListView?.isDragEnabled == true
        menu.findItem(R.id.action_enable_drag).isVisible = mDragListView?.isDragEnabled == false
        menu.findItem(R.id.action_disable_column).isVisible = mDragListView?.isDragEnabled == true
        menu.findItem(R.id.action_enable_column).isVisible = mDragListView?.isDragEnabled == false
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_disable_drag -> {
                mDragListView?.isDragEnabled = false
                requireActivity().invalidateOptionsMenu()
                return true
            }

            R.id.action_enable_drag -> {
                mDragListView?.isDragEnabled = true
                requireActivity().invalidateOptionsMenu()
                return true
            }

            R.id.action_list -> {
                setupListRecyclerView()
                return true
            }

            R.id.action_grid_vertical -> {
                setupGridVerticalRecyclerView()
                return true
            }

            R.id.action_grid_horizontal -> {
                setupGridHorizontalRecyclerView()
                return true
            }

            R.id.disable_swipe_refresh -> {
//                mRefreshLayout?.isRefreshing = !mRefreshLayout?.isRefreshing!!
//                if (mRefreshLayout?.isRefreshing!!) mRefreshLayout?.visibility =
//                    INVISIBLE else mRefreshLayout?.visibility = VISIBLE
//                requireActivity().invalidateOptionsMenu()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
//        (requireActivity() as AppCompatActivity).supportActionBar?.title = "List and Grid"
    }

    private fun setupListRecyclerView() {
        mDragListView!!.setLayoutManager(LinearLayoutManager(context))
        val listAdapter = mItemArray?.let {
            ItemAdapter(
                it, R.layout.list_item, R.id.image, false
            )
        }
        listAdapter?.let { mDragListView!!.setAdapter(it, true) }
        mDragListView!!.setCanDragHorizontally(false)
        mDragListView!!.setCanDragVertically(true)
        mDragListView!!.setCustomDragItem(
            MyDragItem(
                requireContext(), R.layout.list_item
            )
        )
    }

    private fun setupGridVerticalRecyclerView() {
        mDragListView!!.setLayoutManager(GridLayoutManager(context, 4))
        val listAdapter = mItemArray?.let {
            ItemAdapter(
                it, R.layout.grid_item, R.id.item_layout, true
            )
        }
        listAdapter?.let { mDragListView!!.setAdapter(it, true) }
        mDragListView!!.setCanDragHorizontally(true)
        mDragListView!!.setCanDragVertically(true)
        mDragListView!!.setCustomDragItem(null)
    }

    private fun setupGridHorizontalRecyclerView() {
        mDragListView!!.setLayoutManager(
            GridLayoutManager(
                context, 6, LinearLayoutManager.HORIZONTAL, false
            )
        )
        val listAdapter = mItemArray?.let {
            ItemAdapter(
                it, R.layout.grid_item, R.id.item_layout, true
            )
        }
        listAdapter?.let { mDragListView!!.setAdapter(it, true) }
        mDragListView!!.setCanDragHorizontally(true)
        mDragListView!!.setCanDragVertically(true)
        mDragListView!!.setCustomDragItem(null)
    }

    private class MyDragItem constructor(context: Context, layoutId: Int) :
        DragItem(context, layoutId) {
        override fun onBindDragView(clickedView: View, dragView: View) {
            val text = (clickedView.findViewById<View>(R.id.text) as TextView).text
            (dragView.findViewById<View>(R.id.text) as TextView).text = text
            dragView.findViewById<View>(R.id.item_layout)
                .setBackgroundColor(dragView.resources.getColor(R.color.list_item_background, null))
        }
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}

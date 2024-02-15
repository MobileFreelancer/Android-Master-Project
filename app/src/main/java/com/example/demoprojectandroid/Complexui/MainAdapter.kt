package com.example.demoprojectandroid.Complexui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ItemMainBinding


/**
 * Created by Bhumi Shah on 11/7/2017.
 */
class MainAdapter(private val activity: Activity, val mainlist: MutableList<String>, val innerlist: MutableList<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var selectedPosition = -1

    inner class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.binding.tvQuestion.setText(mainlist[position])
        holder.binding.tvQuestionNo.setText((holder.getAdapterPosition() + 1).toString() + ".")

        if (position != selectedPosition) {
            holder.binding.tvQuestion.setTextColor(activity.resources.getColor(R.color.white))
            holder.binding.ivExpand?.setImageResource(R.drawable.ic_more)
            holder.binding.llCollapsed?.setBackgroundColor(activity.resources.getColor(R.color.gray))
            holder.binding.rvSportList?.setVisibility(View.GONE)
        } else {
            holder.binding.tvQuestion.setTextColor(activity.resources.getColor(R.color.white))
            holder.binding.ivExpand?.setImageResource(R.drawable.ic_less)
            holder.binding.llCollapsed?.setBackgroundColor(activity.resources.getColor(R.color.gray))
            holder.binding.rvSportList?.setVisibility(View.VISIBLE)
        }
        holder.binding.llCollapsed?.setOnClickListener(View.OnClickListener {
            if (selectedPosition == position) {
                selectedPosition =  -1
                animateCollapse(holder.binding.rvSportList)
            } else {
                selectedPosition =  position
                animateExpand(holder.binding.rvSportList)
            }
            setInnerRecycleView(holder.binding.rvSportList, innerlist)
            notifyDataSetChanged()
        })


    }
    private fun animateExpand(view: View?) {
        val originalHeight = view?.height ?: 0
        view?.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = view?.measuredHeight ?: 0

        val animatorSet = AnimatorSet()

        // Alpha animation
        val alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
        alphaAnimator.duration = 500
        alphaAnimator.interpolator = AccelerateDecelerateInterpolator()

        // Height animation
        val heightAnimator = ObjectAnimator.ofInt(view, "height", originalHeight, targetHeight)
        heightAnimator.duration = 500
        heightAnimator.interpolator = AccelerateDecelerateInterpolator()

        animatorSet.playTogether(alphaAnimator, heightAnimator)
        animatorSet.start()
    }

    private fun animateCollapse(view: View?) {
        val originalHeight = view?.height ?: 0
        val targetHeight = 0

        val animatorSet = AnimatorSet()

        // Alpha animation
        val alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
        alphaAnimator.duration = 500
        alphaAnimator.interpolator = AccelerateDecelerateInterpolator()

        // Height animation
        val heightAnimator = ObjectAnimator.ofInt(view, "height", originalHeight, targetHeight)
        heightAnimator.duration = 500
        heightAnimator.interpolator = AccelerateDecelerateInterpolator()

        animatorSet.playTogether(alphaAnimator, heightAnimator)
        animatorSet.start()
    }
    override fun getItemCount(): Int {
        return mainlist.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }



    fun setInnerRecycleView(recycleView: RecyclerView, innerlist: List<String>) {
        val innerAdapter = InnerAdapter(activity, innerlist)
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycleView.layoutManager = mLayoutManager
        recycleView.isNestedScrollingEnabled = false
        innerAdapter.setHasStableIds(true)
        recycleView.adapter = innerAdapter
    }

    fun getlist() {}

    companion object {
        private const val TAG = "TestAdapter"
    }
}
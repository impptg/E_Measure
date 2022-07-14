package com.pptg.e_measure.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout

class EMCollapsingToolbarLayout : CollapsingToolbarLayout {
    var state: CollapsingToolbarLayoutState? = null

    enum class CollapsingToolbarLayoutState {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    private var mOnStateChangedListener: OnStateChangedListener? = null
    fun setOnStateChangedListener(onStateChangedListener: OnStateChangedListener?) {
        if (parent !is AppBarLayout) {
            return
        }
        mOnStateChangedListener = onStateChangedListener
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val parent = parent
        if (mOnStateChangedListener != null) {
            setStateChangedListener()
        }
    }

    private fun setStateChangedListener() {
        val appBarLayout = parent as AppBarLayout
        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    //修改状态标记为展开
                    state = CollapsingToolbarLayoutState.EXPANDED
                    mOnStateChangedListener?.onExpanded()
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    //修改状态标记为折叠
                    state = CollapsingToolbarLayoutState.COLLAPSED
                    mOnStateChangedListener?.onCollapsed()
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                        //由折叠变为中间状态时
                        mOnStateChangedListener?.onInternediateFromCollapsed()
                    } else if (state == CollapsingToolbarLayoutState.EXPANDED) {
                        mOnStateChangedListener?.onInternediateFromExpand()
                    }
                    state = CollapsingToolbarLayoutState.INTERNEDIATE
                }
                mOnStateChangedListener?.onInternediate()
            }
        })
    }

    companion object {
        private const val TAG = "StateCollapsingToolbarLayout"
    }
}

interface OnStateChangedListener{
    //展开
    fun onExpanded()

    //折叠
    fun onCollapsed()

    //展开向折叠时的中间状态
    fun onInternediateFromExpand()

    //折叠向展开时的中间状态
    fun onInternediateFromCollapsed()

    //中间状态
    fun onInternediate()
}
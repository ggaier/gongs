package com.github.ggaier.gongs.ui.view

/**
 * Created by wenbo, 2018/11/26
 */
class ViewStateVault {
    private val mValuables = HashMap<String,ViewState>()

    fun saveState(viewState: ViewState) {
        mValuables[viewState.id] = viewState
    }

    fun withdrawState(id: String): ViewState? {
        return mValuables[id]
    }
}

interface StateOwner {

    fun getViewState(): ViewState

    fun restoreViewState(viewState: ViewState)
}

interface ViewState{
    val id: String
}
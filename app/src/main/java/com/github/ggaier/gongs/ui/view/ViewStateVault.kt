package com.github.ggaier.gongs.ui.view

/**
 * Created by wenbo, 2018/11/26
 */
class ViewStateVault<T: State> {

    private val mValuables = HashMap<String,T>()

    fun saveState(viewState: T) {
        mValuables[viewState.id] = viewState
    }

    fun withdrawState(id: String): T? {
        return mValuables[id]
    }
}

interface StateOwner<S: State> {

    fun getViewState(): S

    fun restoreViewState(viewState: S)
}

interface State{
    val id: String
}
package com.example.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.presentation.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.example.presentation.uiModels.FabPosition
import com.example.presentation.uiModels.NavBottomAppState
@BindingAdapter("bottomAppState")
fun BottomAppBar.bindAppState(navBottomAppState: NavBottomAppState?) {
    visibility=if(navBottomAppState?.isBarVisible==false) View.GONE else View.VISIBLE
    bindNavBottomApp(navBottomAppState)
    bindNavigation(navBottomAppState)
}


@BindingAdapter("observeShowEvent")
fun BottomAppBar.bindShowEvent(showData: Boolean) {
    if (showData) performShow() else performHide()
}

fun BottomAppBar.bindNavBottomApp(navBottomAppState: NavBottomAppState?) {

    if (navBottomAppState?.menu == null || navBottomAppState.menu.resMenuId == 0) {
        replaceMenu(R.menu.empty_menu)
        return
    }

    replaceMenu(navBottomAppState.menu.resMenuId)

    setOnMenuItemClickListener { menuItem ->
        navBottomAppState.menu.handle.invoke(menuItem.itemId)
        return@setOnMenuItemClickListener false
    }

    if (navBottomAppState.fabBtnModel != null)
        fabAlignmentMode = getFabPosition(navBottomAppState.fabBtnModel.fabPosition)
}


fun BottomAppBar.bindNavigation(navBottomAppState: NavBottomAppState?) {
    val navigation = navBottomAppState?.navigation

    if (navigation == null) {
        navigationIcon = null
        setNavigationOnClickListener(null)
    } else {
        setNavigationIcon(navigation.resMenuId)
        setNavigationOnClickListener { navigation.navigation.invoke() }
    }
}


private fun getFabPosition(fabPosition: FabPosition): Int = when (fabPosition) {
    FabPosition.CENTER -> BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
    FabPosition.RIGHT -> BottomAppBar.FAB_ALIGNMENT_MODE_END
}
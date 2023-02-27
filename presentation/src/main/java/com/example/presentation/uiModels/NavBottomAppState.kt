package com.example.presentation.uiModels

import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes

data class NavBottomAppState(
        val menu: MenuModel? = null,
        val fabBtnModel: FabBtnModel? = null,
        val navigation: NavigationModel? = null,
        val isBarVisible: Boolean = true
)

data class NavigationModel(
        @DrawableRes val resMenuId: Int,
        val navigation: () -> Unit)

data class MenuModel(
        @MenuRes val resMenuId: Int,
        val handle: (id: Int) -> Unit)


data class FabBtnModel(
        @DrawableRes var imgResId: Int,
        var fabPosition: FabPosition,
        val action: () -> Unit)

enum class FabPosition { CENTER, RIGHT }
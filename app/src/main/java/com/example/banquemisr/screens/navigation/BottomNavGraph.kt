package com.example.banquemisr.screens.navigation


import com.example.banquemisr.R
import com.example.banquemisr.ui.screens.transactionScreen.TransActionScreen


sealed class Screen (
        val route:String
        , val title:String
        , val unselected_icon:Int
        , val selected_icon:Int
    ) {
        object Home : Screen(
            route = "home",
            title = "Home",
            unselected_icon = R.drawable.bar_home,
            selected_icon = R.drawable.bar_s_home
        )

        object Transfer : Screen(
            route = "transferAmount",
            title = "Transfer",
            unselected_icon = R.drawable.bar_transfare,
            selected_icon = R.drawable.bar_s_transfare
        )

        object Transaction : Screen(
            route = "transaction",
            title = "Transaction",
            unselected_icon = R.drawable.bar_transactions,
            selected_icon = R.drawable.bar_s_transactions
        )

        object Card : Screen(
            route = "card",
            title = "Card",
            unselected_icon = R.drawable.bar_cards,
            selected_icon = R.drawable.bar_s_cards
        )

        object More : Screen(
            route = "more",
            title = "More",
            unselected_icon = R.drawable.bar_more,
            selected_icon = R.drawable.bar_s_more
        )
    }
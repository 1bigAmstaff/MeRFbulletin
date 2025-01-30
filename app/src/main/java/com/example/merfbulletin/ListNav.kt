package com.example.merfbulletin

class ListNav {
    private val NavItems = mutableListOf<String>()

    init {
        NavItems.add("This Week's Bulletin")
        NavItems.add("Bulletin Archive")
        NavItems.add("Prayer Requests")
    }

    fun getList(authLevel: AuthorizationLevel): List<String> {
        return when (authLevel) {
            AuthorizationLevel.GUEST -> listOf(NavItems.first())
            AuthorizationLevel.USER -> NavItems
            AuthorizationLevel.ADMIN -> NavItems
        }
    }
}
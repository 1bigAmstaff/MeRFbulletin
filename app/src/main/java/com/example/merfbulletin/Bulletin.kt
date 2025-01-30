package com.example.merfbulletin

class Bulletin {
    private val bulletins = mutableListOf<String>()

    init {
        bulletins.add("This Week's Bulletin")
        bulletins.add("Bulletin Archive")
        bulletins.add("Prayer Requests")
    }

    fun getBulletin(authLevel: AuthorizationLevel): List<String> {
        return when (authLevel) {
            AuthorizationLevel.GUEST -> listOf(bulletins.first())
            AuthorizationLevel.USER -> bulletins
            AuthorizationLevel.ADMIN -> bulletins
        }
    }
}
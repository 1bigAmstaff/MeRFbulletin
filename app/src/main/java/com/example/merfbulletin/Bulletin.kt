package com.example.merfbulletin

class Bulletin {
    private val bulletins = mutableListOf<String>()

    init {
        bulletins.add("First ever bulletin!")
        bulletins.add("Second bulletin!")
        bulletins.add("Third bulletin")
    }

    fun getBulletin(authLevel: AuthorizationLevel): List<String> {
        return when (authLevel) {
            AuthorizationLevel.GUEST -> listOf(bulletins.last())
            AuthorizationLevel.USER -> bulletins
            AuthorizationLevel.ADMIN -> bulletins
        }
    }
}
package com.example.merfbulletin

class BulletinArray {
    private val Bulletins = mutableListOf<String>()

    init {
        Bulletins.add("Last Week's Bulletin")
        Bulletins.add("This Week's Bulletin")
    }

    fun getCurr(): String {
        println(Bulletins.last())
        return Bulletins.last()
//        return when (authLevel) {
//            AuthorizationLevel.GUEST -> listOf(Bulletins.first())
//            AuthorizationLevel.USER -> Bulletins
//            AuthorizationLevel.ADMIN -> Bulletins
//        }
    }
}
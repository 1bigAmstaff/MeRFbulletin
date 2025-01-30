package com.example.merfbulletin

class BulletinArray {
    private val Bulletins = mutableListOf<String>()

    init {
        Bulletins.add("Last Week's Bulletin")
        Bulletins.add("This Week's Bulletin")
    }

    fun getCurr(): String {
        return Bulletins.last()
//        return when (authLevel) {
//            AuthorizationLevel.GUEST -> listOf(Bulletins.first())
//            AuthorizationLevel.USER -> Bulletins
//            AuthorizationLevel.ADMIN -> Bulletins
//        }
    }
    fun getArchive(authLevel: AuthorizationLevel): String {
        return when (authLevel) {
            AuthorizationLevel.GUEST -> Bulletins.first()
            AuthorizationLevel.USER -> Bulletins.joinToString("\n")
            AuthorizationLevel.ADMIN -> Bulletins.joinToString("\n")
        }
    }
}
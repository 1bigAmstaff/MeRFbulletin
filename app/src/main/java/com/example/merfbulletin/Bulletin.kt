package com.example.merfbulletin

class Bulletin {
    fun getBulletin(authLevel: AuthorizationLevel): String {
        val guestBulletin = "Guest Bulletin: Welcome to our app!"
        val userBulletin = "User Bulletin: Here are your updates."
        val adminBulletin = "Admin Bulletin: Here are the admin updates."

        return when (authLevel) {
            AuthorizationLevel.GUEST -> guestBulletin
            AuthorizationLevel.USER -> "$guestBulletin\n$userBulletin"
            AuthorizationLevel.ADMIN -> "$guestBulletin\n$userBulletin\n$adminBulletin"
        }
    }
}
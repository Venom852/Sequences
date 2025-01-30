package ru.netology

import java.time.LocalDate

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val date: LocalDate = LocalDate.of(2000, 1, 1),
    val text: String? = null,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val comments: Comments = Comments(),
    val likes: Likes? = null,
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val canPin: Boolean? = null,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    var attachments: Array<Attachment> = emptyArray<Attachment>()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Post) return false

        return id == other.id &&
                ownerId == other.ownerId &&
                fromId == other.fromId &&
                date == other.date &&
                text == other.text &&
                replyOwnerId == other.replyOwnerId &&
                replyPostId == other.replyPostId &&
                comments == other.comments &&
                likes == other.likes &&
                reposts == other.reposts &&
                views == other.views &&
                canPin == other.canPin &&
                canDelete == other.canDelete &&
                canEdit == other.canEdit &&
                isPinned == other.isPinned &&
                markedAsAds == other.markedAsAds &&
                attachments.contentEquals(other.attachments)
    }
}

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Likes(val count: Int = 0, val userLikes: Boolean = false, val canPublish: Boolean = false)

data class Reposts(val count: Int = 0)

data class Views(val count: Int = 0)

data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val noteId: Int? = null,
    val date: LocalDate = LocalDate.of(2000, 1, 1),
    val text: String = "",
)

data class Note(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: LocalDate = LocalDate.of(2000, 1, 1),
    val comments: Int = 0
)

data class Chat(
    val id: Int = 0,
    val title: String = "",
    val adminId: Int = 0,
    val users: Array<Int> = emptyArray<Int>()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Chat) return false

        return id == other.id &&
                title == other.title &&
                adminId == other.adminId &&
                users.contentEquals(other.users)
    }
}

data class Message(
    val id: Int = 0,
    val chatId: Int = 0,
    val senderId: Int = 0,
    val recipientId: Int = 0,
    val text: String = "",
    val readIt: Boolean = false
)
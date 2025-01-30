package ru.netology

import java.time.LocalDate

object WallService {
    private var id: Int = 0
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var complaints = arrayOf(
        "Спам",
        "Детская порнография",
        "Экстремизм",
        "Насилие",
        "Пропаганда наркотиков",
        "Материал для взрослых",
        "Оскорбление",
        "Призывы к суициду"
    )

    fun notifyAboutNegativeComments(commentId: Int, reason: Int): String {
        for (comment in comments) {
            if (comment.id !== commentId) {
                return throw CommentNotFoundException("Ошибка! Комментария с данным номером не существует!")
            }
        }
        for ((index, complaint) in complaints.withIndex()) {
            if (reason === index) {
                return "Благодарим за внимание! Ваша жалоба '$complaint' отправлена модераторам на проверку."
            }
        }
        return throw ComplaintNotFoundException("Ошибка! Жалобы с данным номером не существует!")
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id === postId) {
                comments += comment
                return comment
            }
        }
        return throw PostNotFoundException("Ошибка! Поста с данным номером не существует!")
    }

    fun addAttachments(post: Post, attachment: Attachment) {
        post.attachments += attachment
    }

    fun conclusionAttachments(post: Post) {
        for (attachment in post.attachments) {
            println(attachment)
        }
    }

    fun clear() {
        posts = emptyArray()
        id = 0
    }

    fun add(post: Post): Post {
        id++
        posts += post.copy(id = id)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postArray) in posts.withIndex()) {
            if (post.id === postArray.id) {
                posts[index] = post.copy(
                    id = id + 1,
                    ownerId = 1,
                    fromId = 1,
                    date = LocalDate.of(2024, 12, 19),
                    text = "Пост о рыбалке",
                    replyOwnerId = 1,
                    replyPostId = 1,
                    comments = Comments(
                        count = 1,
                        canPost = true,
                        groupsCanPost = true,
                        canClose = true,
                        canOpen = true
                    ),
                    likes = Likes(count = 1, userLikes = true, canPublish = true),
                    reposts = Reposts(count = 1),
                    views = Views(count = 1),
                    canPin = true,
                    canDelete = true,
                    canEdit = true,
                    isPinned = true,
                    markedAsAds = true
                )
                return true
            }
        }
        return false
    }
}
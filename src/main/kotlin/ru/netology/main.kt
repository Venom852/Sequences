package ru.netology

fun main() {
    val message = Message()
    val chat = Chat()
    val chatService = ChatService

    println(chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов"))
    println(chatService.createChatAndMessage(message, 3, 4, "Привет Константин", chat, "Константин Егоров"))
    println(chatService.createChatAndMessage(message, 5, 6, "Здравствуйте, Владимир", chat, "Владимир Зубаков"))
    println()

    chatService.markMessageAsRead(1)
    println()

    println(chatService.createMessage(message, 3, 5, 6,
            "Мы рассмотрели Вашу просьбу, свяжитесь с нами.", chat, "Владимир Зубаков"))
    println(chatService.createMessage(message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"))
    println()

    chatService.markMessageAsRead(4)
    println()

    println(chatService.createMessage(message, 1, 1, 2,
            "Всё хорошо, как у вас?", chat, "Николай Горохов"))
    println()

    chatService.markMessageAsRead(5)
    println()

    println(chatService.createMessage(message, 1, 2, 1,
            "Всё отлично! Только что вернулся в город. Я очень устал, но мне хотелось бы с Вами увидеться.",
            chat, "Николай Горохов"))
    println(chatService.createMessage(message, 1, 2, 1,
            "Мне очень нужна Ваша помощь!", chat, "Николай Горохов"))
    println()

    println(chatService.getChats())
    println()

    println(chatService.getUnreadChatsCount())
    println()

    println(chatService.listRecentMessagesFromChats())
    println()

    println(chatService.deleteChat(2))
    println()

    println(chatService.deleteMessage(8))
    println()

    println(chatService.editMessage(7, "Всё отлично! Только что вернулся в город. Мне хотелось бы с Вами увидеться."))
    println()

    println(chatService.createMessage(message, 1, 1, 2,
            "С удовольствием встречусь с Вами.", chat, "Николай Горохов"))
    println()

    println(chatService.getListChatMessagesByIdAndQuantity(2, 2, 2))
    println()
}
package ru.netology

object ChatService {
    private var counterChatId: Int = 0
    private var counterMessageId: Int = 0
    private var chats = mutableListOf<Chat>()
    private var messages = mutableListOf<Message>()

    fun clear() {
        counterChatId = 0
        counterMessageId = 0
        chats = mutableListOf<Chat>()
        messages = mutableListOf<Message>()
    }

    fun createChatAndMessage(
        message: Message,
        senderId: Int,
        recipientId: Int,
        text: String,
        chat: Chat,
        nameRecipient: String
    ): String {
        counterChatId++
        chats += chat.copy(
            id = counterChatId,
            title = nameRecipient,
            adminId = senderId,
            users = arrayOf(senderId, recipientId)
        )
        println("Создан чат с пользователем $nameRecipient.")

        return createMessage(message, counterChatId, senderId, recipientId, text, chat, nameRecipient)
    }

    fun createMessage(
        message: Message,
        chatId: Int,
        senderId: Int,
        recipientId: Int,
        text: String,
        chat: Chat,
        nameRecipient: String
    ): String {
        counterMessageId++
        if (chats.any { it.id === chatId }) {
            messages += message.copy(
                id = counterMessageId,
                chatId = chatId,
                senderId = senderId,
                recipientId = recipientId,
                text = text
            )
            return "Сообщение отправлено!"
        }
        return createChatAndMessage(message, senderId, recipientId, text, chat, nameRecipient)
    }

    fun deleteChat(chatId: Int): String {
        if (chats.any { it.id === chatId }) {
            chats = chats.asSequence().filter { it.id !== chatId }.toMutableList()
            messages = messages.asSequence().filter { it.chatId !== chatId }.toMutableList()
            return "Чат и все сообщения к нему успешно удалены!"
        }
        return throw ChatNotFoundException("Ошибка! Чата с данным номером не существует!")
    }

    fun deleteMessage(messageId: Int): String {
        if (messages.any { it.id === messageId }) {
            messages = messages.asSequence().filter { it.chatId !== messageId }.toMutableList()
            return "Сообщение успешно удалено!"
        }
        return throw MessageNotFoundException("Ошибка! Сообщения с данным номером не существует!")
    }

    fun markMessageAsRead(vararg listMessageId: Int) {
        for ((index, message) in messages.withIndex()) {
            for (messageId in listMessageId) {
                if (message.id === messageId) {
                    messages[index] = message.copy(readIt = true)
                    println("Сообщение прочитано!")
                    break
                }
            }
        }
    }

    fun editMessage(messageId: Int, text: String): String {
        for ((index, message) in messages.withIndex()) {
            if (message.id === messageId) {
                messages[index] = message.copy(text = text)
                return "Сообщение успешно изменено!"
            }
        }
        return throw MessageNotFoundException("Ошибка! Сообщения с данным номером не существует!")
    }

    fun getChats(): MutableList<Chat> {
        return chats
    }

    fun getUnreadChatsCount(): String {
        val numberUnreadChats = messages.asSequence().filter { !it.readIt }.distinctBy { it.chatId }.count()
        return "Количество непрочитанных чатов - $numberUnreadChats."
    }

    fun listRecentMessagesFromChats(): String {
        if (messages.isEmpty()) {
            return "Нет сообщений!"
        }
        return messages.asReversed().asSequence().distinctBy { it.chatId }.sortedBy { it.chatId }.toMutableList().toString()
    }

    /*  Чтобы вернуть сообщения с начала списка, в параметр "sideOfTheList" следует ввести аргумент "1",
    чтобы вернуть с конца списка - любое другое число. */
    fun getListChatMessagesByIdAndQuantity(
        interlocutorId: Int,
        quantityMessages: Int,
        sideOfTheList: Int
    ): List<Message> {
        if (sideOfTheList === 1) {
            markMessageAsRead(
                *messages.asSequence().filter { interlocutorId === it.recipientId }.take(quantityMessages).toMutableList()
                    .map { it.id }.toIntArray()
            )
            return messages.asSequence().filter { interlocutorId === it.recipientId }.take(quantityMessages).toList()
        } else {
            markMessageAsRead(
                *messages.asSequence().filter { interlocutorId === it.recipientId }.toMutableList().takeLast(quantityMessages)
                    .map { it.id }.toIntArray()
            )
            return messages.asSequence().filter { interlocutorId === it.recipientId }.toList().takeLast(quantityMessages)
        }
    }
}
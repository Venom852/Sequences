package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun createChatOneAndMessageOne() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        val result = chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")

        assertEquals("Сообщение отправлено!", result)
    }

    @Test
    fun createMessageTwo() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        val result = chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )

        assertEquals("Сообщение отправлено!", result)
    }

    @Test
    fun createMessageCreateChat() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        val result = chatService.createMessage(
            message, 0, 3, 4, "Привет Константин", chat, "Константин Егоров")

        assertEquals("Сообщение отправлено!", result)
    }

    @Test
    fun deleteChatOne() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1, "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        val result = chatService.deleteChat(1)

        assertEquals("Чат и все сообщения к нему успешно удалены!", result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun deleteChatCreatingExceptionAboutChat() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        chatService.deleteChat(0)
    }

    @Test
    fun deleteMessageTwo() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        val result = chatService.deleteMessage(2)

        assertEquals("Сообщение успешно удалено!", result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun deleteMessageCreatingExceptionAboutMessage() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        chatService.deleteMessage(0)
    }

    @Test
    fun markMessageTwoAsRead() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        val result = chatService.markMessageAsRead(2)

        assertEquals(Unit, result)
    }

    @Test
    fun editMessageTwo() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        val result = chatService.editMessage(2, "Привет Алексей")

        assertEquals("Сообщение успешно изменено!", result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun editMessageCreatingExceptionAboutMessage() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 2, 1,
            "Привет Алексей, как дела?", chat, "Николай Горохов"
        )
        chatService.editMessage(0, "Привет Алексей")
    }

    @Test
    fun getChatsOne() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        val result = chatService.getChats()
        val arrayChats = mutableListOf(Chat(1, "Николай Горохов", adminId = 1, users = arrayOf(1, 2)))

        assertEquals(arrayChats, result)
    }

    @Test
    fun getUnreadChatsCountOneAndTwo() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createChatAndMessage(message, 3, 4, "Привет Константин", chat, "Константин Егоров")
        val result = chatService.getUnreadChatsCount()

        assertEquals("Количество непрочитанных чатов - 2.", result)
    }

    @Test
    fun listRecentMessagesFromChatsNoMessages() {
        val chatService = ChatService

        val result = chatService.listRecentMessagesFromChats()

        assertEquals("Нет сообщений!", result)
    }

    @Test
    fun listRecentMessagesFromChatsOneAndTwo() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createChatAndMessage(message, 3, 4, "Привет Константин", chat, "Константин Егоров")
        val arrayMessages = mutableListOf(
            Message(1, chatId = 1, senderId = 1, recipientId = 2, text = "Привет Николай"),
            Message(2, chatId = 2, senderId = 3, recipientId = 4, text = "Привет Константин")
        ).toString()
        val result = chatService.listRecentMessagesFromChats()

        assertEquals(arrayMessages, result)
    }

    @Test
    fun getListChatMessagesByIdAndQuantityFirstTheList() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 1, 2,
            "Всё хорошо, как у вас?", chat, "Николай Горохов"
        )
        chatService.createMessage(
            message, 1, 1, 2,
            "С удовольствием встречусь с Вами.", chat, "Николай Горохов"
        )
        val listMessages = listOf(
            Message(1, 1, 1, 2, "Привет Николай", readIt = true),
            Message(2, 1, 1, 2, "Всё хорошо, как у вас?", readIt = true)
        )
        val result = chatService.getListChatMessagesByIdAndQuantity(2, 2, 1)

        assertEquals(listMessages, result)
    }

    @Test
    fun getListChatMessagesByIdAndQuantityBelowTheList() {
        val message = Message()
        val chat = Chat()
        val chatService = ChatService

        chatService.createChatAndMessage(message, 1, 2, "Привет Николай", chat, "Николай Горохов")
        chatService.createMessage(
            message, 1, 1, 2,
            "Всё хорошо, как у вас?", chat, "Николай Горохов"
        )
        chatService.createMessage(
            message, 1, 1, 2,
            "С удовольствием встречусь с Вами.", chat, "Николай Горохов"
        )
        val listMessages = listOf(
            Message(2, 1, 1, 2, "Всё хорошо, как у вас?", readIt = true),
            Message(3, 1, 1, 2, "С удовольствием встречусь с Вами.", readIt = true)
        )
        val result = chatService.getListChatMessagesByIdAndQuantity(2, 2, 2)

        assertEquals(listMessages, result)
    }
}
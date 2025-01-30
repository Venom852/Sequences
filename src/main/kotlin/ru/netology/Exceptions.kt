package ru.netology

import java.lang.RuntimeException

class PostNotFoundException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : RuntimeException(message)

class ComplaintNotFoundException(message: String) : RuntimeException(message)

class NoteNotFoundException(message: String) : RuntimeException(message)

class MessageNotFoundException(message: String) : RuntimeException(message)

class ChatNotFoundException(message: String) : RuntimeException(message)
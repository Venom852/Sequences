package ru.netology

import java.lang.RuntimeException

object NoteService {
    private var counterNoteId: Int = 0
    private var counterCommentId: Int = 0
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var deletedComments = mutableListOf<Comment>()

    fun clear() {
        counterNoteId = 0
        counterCommentId = 0
        notes = mutableListOf<Note>()
        comments = mutableListOf<Comment>()
        deletedComments = mutableListOf<Comment>()
    }

    fun addNote(note: Note, title: String, text: String): Note {
        counterNoteId++
        notes += note.copy(id = counterNoteId, title = title, text = text)
        println("Заметка успешно добавлена!")
        return notes.last()
    }

    fun createComment(comment: Comment, noteId: Int, text: String): Comment {
        counterCommentId++
        for (note in notes) {
            if (note.id === noteId) {
                comments += comment.copy(id = counterCommentId, noteId = noteId, text = text)
                println("Комментарий успешно добавлен!")
                return comments.last()
            }
        }
        return throw NoteNotFoundException("Ошибка! Заметки с данным номером не существует!")
    }

    fun deleteNote(noteId: Int): String {
        for (note in notes) {
            if (note.id === noteId) {
                notes.remove(note)
                val commentsIterator = comments.iterator()
                while (commentsIterator.hasNext()) {
                    val iterator = commentsIterator.next()
                    if (iterator.noteId === noteId) {
                        commentsIterator.remove()
                    }
                }
                return "Заметка и все комментарии к ней успешно удалены!"
            }
        }
        return throw NoteNotFoundException("Ошибка! Заметки с данным номером не существует!")
    }

    fun deleteComment(commentId: Int): String {
        for (comment in comments) {
            if (comment.id === commentId) {
                deletedComments += comment
                comments.remove(comment)
                return "Комментарий успешно удален!"
            }
        }
        return throw CommentNotFoundException("Ошибка! Комментария с данным номером не существует!")
    }

    fun editNote(noteId: Int, title: String, text: String): Note {
        for ((index, note) in notes.withIndex()) {
            if (note.id === noteId) {
                notes[index] = note.copy(title = title, text = text)
                println("Заметка успешно изменена!")
                return notes[index]
            }
        }
        return throw NoteNotFoundException("Ошибка! Заметки с данным номером не существует!")
    }

    fun editComment(commentId: Int, text: String): Comment {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id === commentId) {
                comments[index] = comment.copy(text = text)
                println("Комментарий успешно изменен!")
                return comments[index]
            }
        }
        return throw CommentNotFoundException("Ошибка! Комментария с данным номером не существует!")
    }

    fun getNotes(vararg arrayNoteId: Int): MutableList<Note> {
        val listNotes = mutableListOf<Note>()
        val nonexistentNoteId = mutableListOf<Int>()
        for (note in notes) {
            for (noteId in arrayNoteId) {
                if (note.id === noteId) {
                    listNotes += note
                    break
                }
            }
        }
        for (noteId in arrayNoteId) {
            nonexistentNoteId += noteId
        }
        for (note in listNotes) {
            for (noteId in arrayNoteId) {
                if (note.id === noteId) {
                    nonexistentNoteId.remove(noteId)
                    break
                }
            }
        }
        if (nonexistentNoteId.firstOrNull() != null) {
            println("Заметки с номером $nonexistentNoteId не существует!")
        }
        return listNotes
    }

    fun getByIdNote(noteId: Int): Note {
        for (note in notes) {
            if (note.id === noteId) {
                return note
            }
        }
        return throw NoteNotFoundException("Ошибка! Заметки с данным номером не существует!")
    }

    fun getComments(noteId: Int): MutableList<Comment> {
        val listComments = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteId === noteId) {
                listComments += comment
            }
        }
        if (listComments.firstOrNull() == null) {
            return throw NoteNotFoundException("Ошибка! Заметки с данным номером не существует!")
        }
        return listComments
    }

    fun restoreComment(commentId: Int): String {
        for (comment in deletedComments) {
            if (comment.id === commentId) {
                comments += comment
                deletedComments.remove(comment)
                return "Комментарий успешно восстановлен!"
            }
        }
        return throw CommentNotFoundException("Ошибка! Комментария с данным номером не существует!")
    }
}
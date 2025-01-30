package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addNoteOne() {
        val note = Note()
        val noteService = NoteService
        val result = noteService.addNote(note, "Приборка", "Помыть пол")

        assertEquals(Note(1, "Приборка", "Помыть пол"), result)
    }

    @Test
    fun createCommentOne() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        val result = noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")

        assertEquals(Comment(1, noteId = 1, text = "Сделать уборку как можно быстрее"), result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentCreatingExceptionAboutNote() {
        val comment = Comment()
        val noteService = NoteService
        noteService.createComment(comment, 0, "Сделать уборку как можно быстрее")
    }

    @Test
    fun deleteNoteOne() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.createComment(comment, 1, "Купить моющее средство")
        val result = noteService.deleteNote(1)

        assertEquals("Заметка и все комментарии к ней успешно удалены!", result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteCreatingExceptionAboutNote() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.deleteNote(4)
    }

    @Test
    fun deleteCommentOne() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        val result = noteService.deleteComment(1)

        assertEquals("Комментарий успешно удален!", result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentCreatingExceptionAboutComment() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.deleteComment(0)
    }

    @Test
    fun editNoteOne() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        val result = noteService.editNote(1, "Приборка", "Протереть пыль на полках и поставить книги")

        assertEquals(Note(1, "Приборка", "Протереть пыль на полках и поставить книги"), result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteCreatingExceptionAboutNote() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.editNote(0, "Приборка", "Протереть пыль на полках и поставить книги")
    }

    @Test
    fun editCommentOne() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        val result = noteService.editComment(1, "Купить моющее средство и швабру")

        assertEquals(Comment(1, noteId = 1, text = "Купить моющее средство и швабру"), result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentCreatingExceptionAboutComment() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.editComment(0, "Купить моющее средство и швабру")
    }

    @Test
    fun getNotesOneTwo() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.addNote(note, "Приборка", "Протереть пыль на полках")
        val result = noteService.getNotes(1, 2, 3)
        val listNotes = listOf(Note(1, "Приборка", "Помыть пол"), Note(2, "Приборка", "Протереть пыль на полках"))

        assertEquals(listNotes, result)
    }

    @Test
    fun getByIdNoteOne() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        val result = noteService.getByIdNote(1)

        assertEquals(Note(1, "Приборка", "Помыть пол"), result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdNoteCreatingExceptionAboutNote() {
        val note = Note()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.getByIdNote(0)
    }

    @Test
    fun getCommentsOneTwo() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.createComment(comment, 1, "Купить моющее средство")
        val result = noteService.getComments(1)
        val listComments = listOf(
            Comment(1, noteId = 1, text = "Сделать уборку как можно быстрее"),
            Comment(2, noteId = 1, text = "Купить моющее средство")
        )

        assertEquals(listComments, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsCreatingExceptionAboutNote() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.createComment(comment, 1, "Купить моющее средство")
        noteService.getComments(0)
    }

    @Test
    fun restoreCommentOne() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.deleteComment(1)
        val result = noteService.restoreComment(1)

        assertEquals("Комментарий успешно восстановлен!", result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentCreatingExceptionAboutComment() {
        val note = Note()
        val comment = Comment()
        val noteService = NoteService
        noteService.addNote(note, "Приборка", "Помыть пол")
        noteService.createComment(comment, 1, "Сделать уборку как можно быстрее")
        noteService.deleteComment(1)
        noteService.restoreComment(0)
    }
}
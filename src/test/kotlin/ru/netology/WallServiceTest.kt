package ru.netology

import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Test
    fun addPost() {
        val post = Post()
        val service = WallService
        val result = service.add(post)

        assertEquals(Post(id = 1), result)
    }

    @Test
    fun addAttachmentsPhoto() {
        val post = Post()
        val service = WallService
        val photo = PhotoAttachment()
        val result = service.addAttachments(post, photo)

        assertEquals(Unit, result)
    }

    @Test
    fun addAttachmentsVideo() {
        val post = Post()
        val service = WallService
        val video = VideoAttachment()
        val result = service.addAttachments(post, video)

        assertEquals(Unit, result)
    }

    @Test
    fun addAttachmentsAudio() {
        val post = Post()
        val service = WallService
        val audio = AudioAttachment()
        val result = service.addAttachments(post, audio)

        assertEquals(Unit, result)
    }

    @Test
    fun addAttachmentsFile() {
        val post = Post()
        val service = WallService
        val file = FileAttachment()
        val result = service.addAttachments(post, file)

        assertEquals(Unit, result)
    }

    @Test
    fun addAttachmentsUserGift() {
        val post = Post()
        val service = WallService
        val userGift = UserGiftAttachment()
        val result = service.addAttachments(post, userGift)

        assertEquals(Unit, result)
    }

    @Test
    fun conclusionAttachmentsAttachments() {
        val post = Post()
        val service = WallService
        val photo = PhotoAttachment()
        val video = VideoAttachment()
        val audio = AudioAttachment()
        val file = FileAttachment()
        val userGift = UserGiftAttachment()

        service.addAttachments(post, photo)
        service.addAttachments(post, video)
        service.addAttachments(post, audio)
        service.addAttachments(post, file)
        service.addAttachments(post, userGift)

        val result = service.conclusionAttachments(post)

        assertEquals(Unit, result)
    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExistingId() {
        val post = Post(id = 1)
        val service = WallService
        service.add(Post())

        val result = service.update(post)

        assertTrue(result)
    }

    @Test
    fun updateNotExistingId() {
        val post = Post()
        val service = WallService
        service.add(Post())

        val result = service.update(post)

        assertFalse(result)
    }

    @Test
    fun createCommentCommentAdd() {
        val post = Post()
        val service = WallService
        val comment = Comment()

        service.add(post)
        val result = service.createComment(1, comment)

        assertEquals(Comment(), result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentThrowingAnExceptionPost() {
        val post = Post()
        val service = WallService
        val comment = Comment()

        service.add(post)
        service.createComment(0, comment)
    }
    
    @Test
    fun notifyAboutNegativeCommentsAddingComplaint() {
        val post = Post()
        val service = WallService
        val comment = Comment()

        service.add(post)
        service.createComment(1, comment)
        val result = service.notifyAboutNegativeComments(0, 6)

        assertEquals("Благодарим за внимание! Ваша жалоба 'Оскорбление' отправлена модераторам на проверку.", result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun notifyAboutNegativeCommentsThrowingAnExceptionComment() {
        val post = Post()
        val service = WallService
        val comment = Comment()

        service.add(post)
        service.createComment(1, comment)
        service.notifyAboutNegativeComments(1, 6)
    }

    @Test(expected = ComplaintNotFoundException::class)
    fun notifyAboutNegativeCommentsThrowingAnExceptionComplaint() {
        val post = Post()
        val service = WallService
        val comment = Comment()

        service.add(post)
        service.createComment(1, comment)
        service.notifyAboutNegativeComments(0, 8)
    }
}
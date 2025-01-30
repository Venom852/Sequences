package ru.netology

interface Attachment {
    val type: String
}

data class PhotoAttachment(override val type: String = "Photo") : Attachment {
    val photo: Photo = Photo()
}

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val text: String = ""
)

data class VideoAttachment(override val type: String = "Video") : Attachment {
    val video: Video = Video()
}

data class Video(
    val id: Int = 0,
    val title: String = "",
    val ownerId: Int = 0,
    val description: String = "",
    val duration: Int = 0
)

data class AudioAttachment(override val type: String = "Audio") : Attachment {
    val audio: Audio = Audio()
}

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0
)

data class FileAttachment(override val type: String = "File") : Attachment {
    val file: File = File()
}

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0,
    val ext: String = ""
)

data class UserGiftAttachment(override val type: String = "UserGift") : Attachment {
    val userGift: UserGift = UserGift()
}

data class UserGift(
    val id: Int = 0,
    val fromId: Int = 0,
    val message: String = ""
)
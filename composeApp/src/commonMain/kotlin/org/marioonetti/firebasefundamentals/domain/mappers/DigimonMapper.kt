package org.marioonetti.firebasefundamentals.domain.mappers

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonFirebaseDto
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi

fun DigimonDto.toDigimonUi(): DigimonUi {
    return DigimonUi(
        name = name ?: "",
        imageUrl = img ?: "",
        level = level ?: "",
    )
}

fun DigimonEntity.toDigimonUi(): DigimonUi {
    return DigimonUi(
        name = name,
        imageUrl = imageUrl,
        level = level,
        description = description,
    )
}

fun DigimonUi.toDigimonEntity(): DigimonEntity {
    return DigimonEntity(
        name = name,
        imageUrl = imageUrl,
        level = level,
        description = description ?: "",
    )
}

fun DigimonUi.toDigimonFirebase(): DigimonFirebaseDto {
    return DigimonFirebaseDto(
        name = name,
        imageUrl = imageUrl,
        level = level,
        description = description ?: "",
    )
}

fun DigimonFirebaseDto.toDigimonUi(): DigimonUi {
    return DigimonUi(
        name = name,
        imageUrl = imageUrl,
        level = level,
        description = description,
    )
}
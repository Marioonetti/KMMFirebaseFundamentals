package org.marioonetti.firebasefundamentals.domain.models.mock

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto

val mockDigimonList = listOf(
    DigimonDto(
        name = "Agumon",
        img = "https://digimon.shadowsmith.com/img/agumon.jpg",
        level = "Rookie"
    ),
    DigimonDto(
        name = "Tsunomon",
        img = "https://digimon.shadowsmith.com/img/tsunomon.jpg",
        level = "Rookie"
    ),
)
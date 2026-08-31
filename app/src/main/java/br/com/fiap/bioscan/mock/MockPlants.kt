package br.com.fiap.bioscan.mock

import br.com.fiap.bioscan.model.Plant

val mockPlants = listOf(
    Plant(
        id = 1,
        userId = 1,
        scientificName = "Monstera deliciosa",
        commonName = "Costela-de-Adão",
        family = "Araceae",
        genus = "Monstera",
        imageUrl = "https://...",
        score = 0.98
    ),
    Plant(
        id = 2,
        userId = 1,
        scientificName = "Helianthus annuus",
        commonName = "Girassol",
        family = "Asteraceae",
        genus = "Helianthus",
        imageUrl = "https://...",
        score = 0.95
    ),
    Plant(
        id = 3,
        userId = 1,
        scientificName = "Lavandula angustifolia",
        commonName = "Lavanda",
        family = "Lamiaceae",
        genus = "Lavandula",
        imageUrl = "https://...",
        score = 0.91
    ),
    Plant(
        id = 4,
        userId = 1,
        scientificName = "Ocimum basilicum",
        commonName = "Manjericão",
        family = "Lamiaceae",
        genus = "Ocimum",
        imageUrl = "https://...",
        score = 0.89
    )
)
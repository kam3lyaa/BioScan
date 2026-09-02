package br.com.fiap.bioscan.api

import br.com.fiap.bioscan.model.Plant

data class PlantNetResponse(
    val bestMatch: String?,
    val results: List<PlantResult>
)

data class PlantResult(
    val score: Double,
    val species: Species
)

data class Species(
    val scientificNameWithoutAuthor: String,
    val scientificNameAuthorship: String?,
    val genus: Genus,
    val family: Family,
    val commonNames: List<String>?
)

data class Genus(
    val scientificNameWithoutAuthor: String
)

data class Family(
    val scientificNameWithoutAuthor: String
)

fun PlantNetResponse.toPlant(userId: Long, imageUrl: String? = null): Plant? {
    val bestResult = this.results.firstOrNull() ?: return null

    return Plant(
        userId = userId,
        scientificName = bestResult.species.scientificNameWithoutAuthor,
        commonName = bestResult.species.commonNames?.firstOrNull(),
        family = bestResult.species.family.scientificNameWithoutAuthor,
        genus = bestResult.species.genus.scientificNameWithoutAuthor,
        imageUrl = imageUrl,
        score = bestResult.score
    )
}
package br.com.fiap.bioscan.api

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
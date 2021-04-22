package com.challenge.b2w.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "planets")
data class Planet(
    @Id
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long,
    val nome: String,
    val clima: String,
    val terreno: String,
    @JsonProperty(value = "filmes", access = JsonProperty.Access.READ_ONLY)
    var filmes :Int
)

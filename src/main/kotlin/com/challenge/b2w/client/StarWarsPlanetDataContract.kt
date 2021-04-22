package com.challenge.b2w.client

data class StarWarsPlanetDataContract(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)
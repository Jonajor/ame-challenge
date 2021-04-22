package com.challenge.b2w.repository

import com.challenge.b2w.model.Planet
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface PlanetRepository : ReactiveCrudRepository<Planet, Long>{
    fun findByNome(nome: String): Mono<Planet>
}
package com.challenge.b2w.controller

import com.challenge.b2w.model.Planet
import com.challenge.b2w.service.PlanetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

import reactor.core.publisher.Mono
import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/starwars/v1")
class PlanetController(val planetService: PlanetService) {

    @GetMapping
    fun getAllTweets(): Flux<Planet> {
        return planetService.findAll()
    }

    @GetMapping("/{id}")
    fun getTweetById(@PathVariable(value = "id") id: Long): Mono<ResponseEntity<Planet>>? {
        return planetService.findById(id)
            .map { savedTweet -> ResponseEntity.ok(savedTweet) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PostMapping
     fun createTweets(@Validated @RequestBody planet: Planet): Mono<Planet> {
        var teste2 = planetService.saveCoroutiner(planet)
        return teste2
    }

    @DeleteMapping("/{id}")
    fun deleteTweet(@PathVariable(value = "id") id: Long): Mono<ResponseEntity<Void>>? {
        return planetService.existById(id)
            .flatMap {
                planetService.delete(id)
                    .then(Mono.just(ResponseEntity<Void>(HttpStatus.OK)))
            }
            .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }

}
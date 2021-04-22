package com.challenge.b2w.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "StarWarsClient", url = "\${startwars.url.planets}")
interface StarWarsClient {
    @GetMapping
    fun getAllPlanets() : StarWarsPlanetDataContract
}

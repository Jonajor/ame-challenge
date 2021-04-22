package com.challenge.b2w.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "StarWarsClient", url = "\${startwars.url.planets}")
interface StarWarsClientw {
    @GetMapping
    fun getAllPlanets() : StarWarsPlanetDataContract
}
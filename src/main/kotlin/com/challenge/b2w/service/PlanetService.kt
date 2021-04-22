package com.challenge.b2w.service

import com.challenge.b2w.client.StarWarsClient
import com.challenge.b2w.client.StarWarsClientw
import com.challenge.b2w.client.StarWarsPlanetDataContract
import com.challenge.b2w.model.Planet
import com.challenge.b2w.repository.PlanetRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PlanetService(private val planetRepository: PlanetRepository) {

    lateinit var starWarsClient: StarWarsClientw
    fun existById(id: Long): Mono<Boolean> {
        return planetRepository.existsById(id)
    }

    fun findAll(): Flux<Planet> {
        return planetRepository.findAll()
    }

    fun findById(id: Long): Mono<Planet> {
        return planetRepository.findById(id)
    }

    fun findByName(nome: String): Mono<Planet>{
        return planetRepository.findByNome(nome)
    }

    fun save(planet: Planet, size: Int): Mono<Planet> {
        planet.filmes = size
        return planetRepository.save(planet)
    }

     fun saveCoroutiner(planet: Planet): Mono<Planet> {
        var teste2 = starWarsClient.getAllPlanets()

        var nome= teste2.results.stream().findAny().get().name
        var quantidadeFilmes = teste2.results.stream().findAny().get().films.size
        return when(nome){
            planet.nome -> save(planet, quantidadeFilmes)
            else -> throw Throwable("teste")
        }
    }


    /*
    fun save(planet: Planet) {
        CompletableFuture.supplyAsync {
            try {
                return@supplyAsync planetRepository.save(planet)
            }catch (e: Exception){
                return@supplyAsync e.message
            }
        }.whenCompleteAsync { result, throwable ->
            var teste = starWarsClient.getAllPlanets()
            teste.toIterable().forEach { it ->
                var nome= it.stream().findAny().get().name
                var quantidadeFilmes = it.stream().findAny().get().films.size
                when(nome){
                    planet.nome -> updatePlanet(planet.nome, quantidadeFilmes)
                }
            }
        }
    }
*/
    private fun updatePlanet(nome: String, quantidadeFilmes: Int): Mono<Planet> {
        return findByName(nome)
            .flatMap { existingPlanet: Planet ->
                existingPlanet.copy(filmes = quantidadeFilmes)
                planetRepository.save(existingPlanet)
            }
    }


    fun delete(id: Long) : Mono<Void> {
        return findById(id).flatMap { existPlanet -> planetRepository.delete(existPlanet) }
    }
}
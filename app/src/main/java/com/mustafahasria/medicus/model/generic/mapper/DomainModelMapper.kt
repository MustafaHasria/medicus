package com.mustafahasria.medicus.model.generic.mapper

// Model: Dto, Entity
interface DomainModelMapper<Model, Domain> {

    fun mapFromModel(model: Model) : Domain

    fun mapFromDomain(domain: Domain) : Model
}
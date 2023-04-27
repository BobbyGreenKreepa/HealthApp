package com.example.healthapp.core.mapper

interface ILayerMapper<TDomain, TOther> {

    fun toDomain(value: TOther): TDomain

    fun fromDomain(value: TDomain): TOther
}

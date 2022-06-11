package com.ayogeshwaran.githubclient.mappers

interface Mapper<I, O> {
    fun map(data: I): O
}
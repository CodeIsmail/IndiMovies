package dev.codeismail.data.mapper

interface BaseMapper<in T, out O> {
    fun map(input: T): O
}

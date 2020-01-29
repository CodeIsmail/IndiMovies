package dev.codeismail.domain.common

interface Mapper <in I, O>{

    operator fun invoke(from: I): O
}
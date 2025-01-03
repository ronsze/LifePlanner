package kr.sdbk.common

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> Json.encodeToStringOrEmpty(value: T?): String = if (value == null) "" else Json.encodeToString(value)
inline fun <reified T> Json.decodeFromStringOrNull(value: String): T? = if (value == "") null else Json.decodeFromString<T>(value)
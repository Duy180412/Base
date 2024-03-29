package com.example.demo.extension

interface Bindable<T> {
    fun bind(item: T)
}

inline fun <reified T> Any?.cast(): T? {
    if (this is T) return this
    return null
}
package com.dicoding.budayai.util

open class Event<out T>(private val content: T) {

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    var hasBeenHandled = false
        private set
}
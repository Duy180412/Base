package com.example.demo.extension

interface Command

interface HasCommandCallback {
    var onCommand: (Command) -> Unit
}

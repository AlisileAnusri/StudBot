package com.example.studbot.utils

object BotResponse {
    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.toLowerCase()
        return when
        {
            message.contains("hello") || message.contains("hey") || message.contains("hola") || message.contains("hi") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Bonjour!"
                    else -> "error" }
            }
            message.contains("thanks") || message.contains("thank you") -> {
               "My pleasure !"
            }
            message.contains("later") || message.contains("gn") || message.contains("see") -> {
                when (random) {
                    0 -> "Tata see ya later"
                    1 -> "Take care"
                    2 -> "Bubyeee"
                    else -> "error" }
            }
            message.contains("doing") || message.contains("what")  -> {
                when (random) {
                    0 -> "Nothing much!"
                    1 -> "Always there to help you bud"
                    2 -> "Mon Amor!"
                    else -> "error" }
            }
            message.contains("exhausted") || message.contains("tired")  -> {
                when (random) {
                    0 -> "No worries! How about we take a small break now?"
                    1 -> "Time for a nap I guess"
                    2 -> "Don't worry, a good sleep would do the work"
                    else -> "error" }
            }
            message.contains("yes") || message.contains("exactly") || message.contains("yeah")  -> {
                    "Lemme set an alarm then. When would you like to resume your studies? Select the time"
            }
            message.contains("done") || message.contains("day")   -> {
                when (random) {
                    0 -> "What's your progress in the plan you made ?"
                    1 -> "Your progress in the study plan"
                    2 -> "Your progress in today's content"
                    else -> "error" }
            }
            message.contains("bye") || message.contains("tata")   -> {
                when (random) {
                    0 -> "PLese, take a minute to rate your experience with me buddy"
                    1 -> "You are an optimistic student. What would you rate me?"
                    2 -> "Please, rate your experience with me bud"
                    else -> "error" }
            }

            else -> {
                when (random) {
                    0 -> "I didn't understand that..."
                    1 -> "Try asking me something different buddy"
                    2 -> "Idk! Not encoded for that pal"
                    else -> "Error"
                }
            }
        }
    }
}
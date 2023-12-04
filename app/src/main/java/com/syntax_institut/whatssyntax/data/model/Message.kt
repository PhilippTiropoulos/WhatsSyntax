package com.syntax_institut.whatssyntax.data.model

import java.time.LocalDateTime
import java.util.Calendar

data class Message(val text: String, val incoming: Boolean, val timestamp: Calendar)
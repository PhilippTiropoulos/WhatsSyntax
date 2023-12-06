package com.syntax_institut.whatssyntax.data.model

data class Call(val contact: Contact, var incoming: Boolean, var accepted: Boolean, var time: String)
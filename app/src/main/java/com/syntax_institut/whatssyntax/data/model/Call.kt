package com.syntax_institut.whatssyntax.data.model

import java.util.Date

data class Call(val contact: Contact, var incoming: Boolean, var accepted: Boolean, var time: Date)
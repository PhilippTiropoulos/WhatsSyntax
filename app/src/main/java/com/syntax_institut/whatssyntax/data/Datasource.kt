package com.syntax_institut.whatssyntax.data

import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.model.Status
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Datasource {


    private var profile = Profile("Max Mustermann", "666-555-4444", R.drawable.pp_01)

    private val contactList = listOf(
        Contact("Anna Smith", "123-456-7890", R.drawable.pp_01, Status("Habe Steinpilze abzugeben.",R.drawable.christine_siracusa_xjy1c5lvnn8_unsplash)),
        Contact("John Doe", "321-654-0987", R.drawable.pp_02, null),
        Contact("Emily Johnson", "111-222-3333", R.drawable.pp_03, null),
        Contact("Tom Brown", "555-666-7777", R.drawable.pp_04, null),
        Contact("Sara Williams", "888-999-0000", R.drawable.pp_05, null),
        Contact("Mike Davis", "246-135-7920", R.drawable.pp_06, Status("Schönes Wetter heute!",R.drawable.wetter)),
        Contact("Lena Wilson", "654-987-3210", R.drawable.pp_07, null),
        Contact("Daniel Taylor", "987-654-3210", R.drawable.pp_01, null),
        Contact("Chris Miller", "444-555-6666", R.drawable.pp_02, null),
        Contact("Sophia Adams", "333-444-5555", R.drawable.pp_03, Status("Meine Katze ist verschwunden, hat sie jemand gesehen?",R.drawable.cat)),
        Contact("Lucas Nelson", "111-999-8888", R.drawable.pp_04, null),
        Contact("Nina Lewis", "777-888-9999", R.drawable.pp_05, null),
        Contact("Eva Clark", "222-333-4444", R.drawable.pp_06, null),
        Contact("Tim Scott", "555-444-3333", R.drawable.pp_07, Status("Liege krank im Bett, nur Notfälle heute.",R.drawable.krank)),
        Contact("Oliver King", "666-555-4444", R.drawable.pp_01, null),
        Contact("Julia Hill", "777-666-5555", R.drawable.pp_02, null),
        Contact("Max Allen", "888-777-6666", R.drawable.pp_03, null),
        Contact("Laura Reed", "999-888-7777", R.drawable.pp_04, null),
        Contact("John Hall", "000-999-8888", R.drawable.pp_05, null),
        Contact("Lisa Baker", "111-000-9999", R.drawable.pp_06, null)
    )

    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    private val callList = listOf(
        Call(contactList[0], incoming = true, true, dateFormat.parse("11.10.2023 15:30")),
        Call(contactList[1], incoming = false, false, dateFormat.parse("11.10.2023 14:20")),
        Call(contactList[2], incoming = true, false, dateFormat.parse("10.10.2023 18:45")),
        Call(contactList[3], incoming = false, true, dateFormat.parse("10.10.2023 12:10")),
        Call(contactList[4], incoming = true, true, dateFormat.parse("09.10.2023 21:30")),
        Call(contactList[5], incoming = true, false, dateFormat.parse("09.10.2023 19:20")),
        Call(contactList[6], incoming = false, false, dateFormat.parse("08.10.2023 08:00")),
        Call(contactList[7], incoming = true, true, dateFormat.parse("08.10.2023 06:15")),
        Call(contactList[8], incoming = false, true, dateFormat.parse("07.10.2023 22:40")),
        Call(contactList[9], incoming = true, false, dateFormat.parse("07.10.2023 21:15")),
        Call(contactList[10], incoming = true, true, dateFormat.parse("07.10.2023 14:50")),
        Call(contactList[11], incoming = false, true, dateFormat.parse("06.10.2023 20:30")),
        Call(contactList[12], incoming = true, false, dateFormat.parse("06.10.2023 11:00")),
        Call(contactList[13], incoming = true, true, dateFormat.parse("05.10.2023 16:25")),
        Call(contactList[14], incoming = false, false, dateFormat.parse("05.10.2023 14:10")),
        Call(contactList[15], incoming = true, true, dateFormat.parse("05.10.2023 09:45")),
        Call(contactList[16], incoming = false, true, dateFormat.parse("04.10.2023 23:30")),
        Call(contactList[17], incoming = true, false, dateFormat.parse("04.10.2023 18:15")),
        Call(contactList[18], incoming = false, false, dateFormat.parse("03.10.2023 16:00")),
        Call(contactList[19], incoming = true, true, dateFormat.parse("03.10.2023 10:30"))
    )


    private fun minutesAgo(minutes: Int): Calendar {
        return Calendar.getInstance().apply {
            add(Calendar.MINUTE, -minutes)
        }
    }
    private val chatList = listOf(
        Chat(contactList[2], mutableListOf(
            Message("Hallo!", true, minutesAgo(15)),
            Message("Hi, wie geht's?", false, minutesAgo(12)),
            Message("Mir geht's gut, danke!", true, minutesAgo(7))
        )
        ),
        Chat(contactList[6], mutableListOf(
            Message("Bist du heute Abend frei?", false, minutesAgo(20)),
            Message("Ja, was ist los?", true, minutesAgo(8))
        )),
        Chat(contactList[8], mutableListOf(
            Message("Wo bist du?", true, minutesAgo(15)),
            Message("Im Büro.", false, minutesAgo(12)),
            Message("Ok, ich bin in 5 Minuten da.", true, minutesAgo(10))
        )),
        Chat(contactList[3], mutableListOf(
            Message("Vergiss nicht, Milch zu kaufen.", false, minutesAgo(13)),
            Message("Werde ich nicht!", true, minutesAgo(12))
        )),
        Chat(contactList[4], mutableListOf(
            Message("Wie spät ist es?", true, minutesAgo(17)),
            Message("Es ist 18 Uhr.", false, minutesAgo(14))
        )),
        Chat(contactList[3], mutableListOf(
            Message("Wann treffen wir uns?", true, minutesAgo(20)),
            Message("Um 17 Uhr.", false, minutesAgo(18)),
            Message("Passt.", true, minutesAgo(16)),
            Message("Super, bis dann!", false, minutesAgo(15))
        )),
        Chat(contactList[5], mutableListOf(
            Message("Was gibt's zum Abendessen?", true, minutesAgo(21)),
            Message("Pizza.", false, minutesAgo(17))
        )),
        Chat(contactList[7], mutableListOf(
            Message("Treffen wir uns morgen?", true, minutesAgo(27)),
            Message("Ja, klingt gut.", false, minutesAgo(25)),
            Message("Um wie viel Uhr?", true, minutesAgo(20))
        )),
        Chat(contactList[8], mutableListOf(
            Message("Hast du die E-Mail bekommen?", false, minutesAgo(33)),
            Message("Noch nicht, ich schaue nach.", true, minutesAgo(28))
        )),
        Chat(contactList[1], mutableListOf(
            Message("Brauchst du etwas?", true, minutesAgo(40)),
            Message("Nein, danke.", false, minutesAgo(35))
        ))
    )

    fun getCalls(): List<Call> {
        return callList
    }

    fun getChats(): List<Chat> {
        return chatList
    }

    fun getContacts(): List<Contact> {
        return contactList
    }

    fun getProfile(): Profile {
        return profile
    }

    fun setProfile(profile: Profile) {
        this.profile = profile
    }

}
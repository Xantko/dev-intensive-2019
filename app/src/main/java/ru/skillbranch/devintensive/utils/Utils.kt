package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var parts: List<String>? = null
        var firstName: String? = null
        var lastName: String? = null
        val fullNameTrim: String

        if (fullName != " " && fullName != "" && fullName != null) {
            fullNameTrim = fullName.trim()
            parts = fullNameTrim.split(" ")
        }

        if (parts?.size == 2) {
            if (parts.get(0) != "" && parts.get(0) != " ") firstName = parts.getOrNull(0)
            if (parts.get(1) != "" && parts.get(1) != " ") lastName = parts.getOrNull(1)
        } else if (parts?.size == 1 && parts.getOrNull(0) != "") {
            firstName = parts.getOrNull(0)
        }

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var result: String? = ""
        if (firstName != null && firstName != "" && firstName != " ") {
            val firstNameTrim: String = firstName.trim()
            result += firstNameTrim[0].toUpperCase().toString()
        }

        if (lastName != null && lastName != "" && lastName != " ") {
            val lastNameTrim: String = lastName.trim()
            result += lastNameTrim[0].toUpperCase().toString()
        }

        if (result == "") result = null

        return result
    }
}
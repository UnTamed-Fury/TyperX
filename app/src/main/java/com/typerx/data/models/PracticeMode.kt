package com.typerx.data.models

enum class PracticeCategory {
    LOWERCASE_ONLY,
    LOWERCASE_UPPERCASE,
    NUMBERS_ONLY,
    NUMBERS_TEXT,
    MIXED_HINDI_ENGLISH,
    WORD_MODE,
    SENTENCE_MODE,
    PARAGRAPH_MODE
}

enum class TimeMode(val seconds: Int) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60),
    ONE_TWENTY(120),
    FIVE_MINUTES(300),
    TEN_MINUTES(600),
    FIFTEEN_MINUTES(900)
}

data class PracticeMode(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val category: PracticeCategory,
    val timeMode: TimeMode? = null, // Null for custom modes
    val customDuration: Int? = null, // For custom time modes
    val customText: String? = null, // For custom text practice
    val language: String = "en" // "en" for English, "hi" for Hindi
)
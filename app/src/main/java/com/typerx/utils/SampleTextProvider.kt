package com.typerx.utils

import com.typerx.data.models.PracticeCategory

object SampleTextProvider {
    
    fun getSampleText(category: PracticeCategory, language: String = "en"): String {
        return when (category) {
            PracticeCategory.LOWERCASE_ONLY -> {
                when (language) {
                    "hi" -> getHindiLowercaseSample()
                    else -> "the quick brown fox jumps over the lazy dog. programming is fun and educational."
                }
            }
            PracticeCategory.LOWERCASE_UPPERCASE -> {
                when (language) {
                    "hi" -> getHindiMixedCaseSample()
                    else -> "The Quick Brown Fox Jumps Over The Lazy Dog. Programming Is Fun And Educational."
                }
            }
            PracticeCategory.NUMBERS_ONLY -> {
                "1234567890 0987654321 5678 8765 3456 6543"
            }
            PracticeCategory.NUMBERS_TEXT -> {
                when (language) {
                    "hi" -> getHindiNumbersTextSample()
                    else -> "The year 2023 brought many innovations. Chapter 5 discusses advanced techniques in 12 steps."
                }
            }
            PracticeCategory.MIXED_HINDI_ENGLISH -> {
                "Hello नमस्ते world प्रोग्रामिंग is आनंददायक and educational शैक्षिक."
            }
            PracticeCategory.WORD_MODE -> {
                when (language) {
                    "hi" -> getHindiWordSample()
                    else -> "word practice typing exercise improves speed accuracy and efficiency skills development"
                }
            }
            PracticeCategory.SENTENCE_MODE -> {
                when (language) {
                    "hi" -> getHindiSentenceSample()
                    else -> "This is a sentence for typing practice. Proper punctuation and spacing are important. Focus on accuracy first, then speed."
                }
            }
            PracticeCategory.PARAGRAPH_MODE -> {
                when (language) {
                    "hi" -> getHindiParagraphSample()
                    else -> "Typing proficiency requires consistent practice and attention to detail. Regular practice sessions help improve both speed and accuracy. " +
                           "Focus on maintaining proper finger positioning and rhythm. Remember that accuracy is more important than speed initially. " +
                           "Over time, speed will naturally increase as muscle memory develops through repeated practice."
                }
            }
        }
    }
    
    private fun getHindiLowercaseSample(): String {
        return "नमस्ते दुनिया यह हिंदी में टाइपिंग अभ्यास के लिए एक उदाहरण है। अधिक अभ्यास से गति में सुधार होता है।"
    }
    
    private fun getHindiMixedCaseSample(): String {
        return "नमस्ते दुनिया यह हिंदी में टाइपिंग अभ्यास के लिए एक उदाहरण है। अधिक अभ्यास से गति में सुधार होता है।"
    }
    
    private fun getHindiNumbersTextSample(): String {
        return "वर्ष 2023 में कई नवाचार आए। अध्याय 5 उन्नत तकनीकों पर चर्चा करता है।"
    }
    
    private fun getHindiWordSample(): String {
        return "टाइपिंग अभ्यास गति सुधार अच्छी आदतें कुशलता विकास अनुभव ज्ञान तकनीक प्रक्रिया विधि विशेषज्ञता"
    }
    
    private fun getHindiSentenceSample(): String {
        return "यह हिंदी में वाक्य अभ्यास के लिए है। उचित विराम चिह्न और अंतर महत्वपूर्ण हैं। सटीकता पर ध्यान केंद्रित करें।"
    }
    
    private fun getHindiParagraphSample(): String {
        return "हिंदी टाइपिंग कौशल के लिए निरंतर अभ्यास की आवश्यकता होती है। नियमित अभ्यास सत्र गति और सटीकता दोनों में सुधार करते हैं। " +
               "उचित उंगली स्थिति और लय को बनाए रखने पर ध्यान केंद्रित करें। याद रखें कि प्रारंभ में गति की तुलना में सटीकता अधिक महत्वपूर्ण है। " +
               "समय के साथ, दोहराए गए अभ्यास के माध्यम से मांसपेशियों की स्मृति विकसित होने पर गति प्राकृतिक रूप से बढ़ जाएगी।"
    }
}
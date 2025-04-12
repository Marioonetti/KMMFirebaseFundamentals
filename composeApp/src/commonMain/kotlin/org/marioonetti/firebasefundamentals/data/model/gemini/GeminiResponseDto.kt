package org.marioonetti.firebasefundamentals.data.model.gemini

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>?,
    val promptFeedback: PromptFeedback? = null
) {
    fun extractText(): String? {
        return candidates?.firstOrNull()
            ?.content?.parts?.firstOrNull()?.text
    }
}

@Serializable
data class Candidate(
    val content: Content,
    val finishReason: String?,
    val index: Int? = null,
    val safetyRatings: List<SafetyRating>? = null
)

@Serializable
data class SafetyRating(
    val category: String,
    val probability: String
)

@Serializable
data class PromptFeedback(
    val safetyRatings: List<SafetyRating>?
)
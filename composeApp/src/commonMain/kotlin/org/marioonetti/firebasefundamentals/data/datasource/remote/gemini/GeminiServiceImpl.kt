package org.marioonetti.firebasefundamentals.data.datasource.remote.gemini

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.marioonetti.firebasefundamentals.data.model.gemini.Content
import org.marioonetti.firebasefundamentals.data.model.gemini.GeminiRequest
import org.marioonetti.firebasefundamentals.data.model.gemini.GeminiResponse
import org.marioonetti.firebasefundamentals.data.model.gemini.Part
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.secrets.ApiKeys

class GeminiServiceImpl(
    private val httpClient: HttpClient,
): GeminiService {
    private val apiKey = ApiKeys.GEMINI_API_KEY
    private val modelName = "gemini-2.0-flash-lite"
    private val apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/$modelName:generateContent"

    override suspend fun generateContent(prompt: String): Either<AppError, String> {
        val requestBody = GeminiRequest(contents = listOf(Content(parts = listOf(Part(text = prompt)))))
        try {
            val response = httpClient.post(apiUrl) {
                url {
                    parameters.append("key", apiKey)
                }
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body<GeminiResponse>()
            val responseText = response.extractText()
            if (responseText != null) {
                return Either.Right(responseText)
            } else {
                val errorReason = response.promptFeedback?.toString() ?: "No text content in response"
                return Either.Left(AppError.Remote(errorReason))
            }
        } catch (e: Exception) {
            return Either.Left(AppError.Remote(e.message ?: "Unknown error"))
        }
    }

}
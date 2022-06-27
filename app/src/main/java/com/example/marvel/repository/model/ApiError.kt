package com.example.marvel.repository.model

sealed class ApiError(val statusCode: Int, var body: String) {
    class InvalidHash(statusCode: Int, body: String): ApiError(statusCode, body)
    class Forbidden(statusCode: Int, body: String): ApiError(statusCode, body)
    class MethodNotAllowed(statusCode: Int, body: String): ApiError(statusCode, body)
    class MissingAPIKey(statusCode: Int, body: String): ApiError(statusCode, body)
    class InvalidRequest(statusCode: Int, body: String): ApiError(statusCode, body)
    class Unknown(statusCode: Int, body: String): ApiError(statusCode, body)
}
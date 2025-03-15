package com.example.workmanageronetime.data.remote

data class EmailVerificationDTO(
    val disposable: Boolean,
    val domain: String,
    val email: String,
    val is_valid: Boolean,
    val mx_records: Boolean
)
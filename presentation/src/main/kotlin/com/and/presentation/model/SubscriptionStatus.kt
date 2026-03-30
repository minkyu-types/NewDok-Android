package com.and.presentation.model

enum class SubscriptionStatus {
    INITIAL, CHECK, CONFIRMED, PAUSED;

    companion object {
        fun from(value: String): SubscriptionStatus =
            entries.find { it.name == value } ?: INITIAL
    }
}

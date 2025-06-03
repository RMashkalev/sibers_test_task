package com.example.features.feed.presentation

sealed interface FeedState {

	data object Initial : FeedState

	data object Content : FeedState

	data object Error : FeedState
}
package com.examenp.recliclerview.data.network.entities.jikan.AllAnimes

data class Pagination(
    val current_page: Int,
    val has_next_page: Boolean,
    val items: Items,
    val last_visible_page: Int
)
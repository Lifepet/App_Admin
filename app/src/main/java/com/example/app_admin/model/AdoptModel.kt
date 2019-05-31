package com.example.app_admin.model

data class AdoptModel(
    var content: String,
    var kakao_id: String,
    var phone: String,
    var creation_date: String,
    var author: String,
    var current_location: String,
    var apply_id: Int
)
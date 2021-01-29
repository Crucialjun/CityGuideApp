package com.example.cityguideapp

data class UserHelper(
    val fullname: String,
    val username: String,
    val email: String,
    val phoneNo: String,
    val password: String,
    val date: String,
    val gender: String,


    ) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}

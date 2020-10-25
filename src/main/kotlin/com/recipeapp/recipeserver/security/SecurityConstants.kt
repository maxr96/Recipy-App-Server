package com.recipeapp.recipeserver.security

const val SIGN_UP_URL = "/sign-up"
const val SECRET = "SecretKeyToGenJWTs"
const val TOKEN_PREFIX = "Bearer "
const val HEADER_STRING = "Authorization"
const val EXPIRATION_TIME: Long = 864_000_000 // 10 days

package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Tag

interface TagService {
    fun getAllByNames(names: List<String>): List<Tag?>
}

package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Tag
import com.recipeapp.recipeserver.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(val repository: TagRepository) : TagService {
    override fun getAllByNames(names: List<String>): List<Tag?> {
        return repository.findAllByNameIn(names)
    }
}

package com.recipyapp.recipyserver.controller

import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipy")
class RecipyController {
    @GetMapping
    fun recipy(model: Model): String {
        model["title"] = "Blog"
        return "blog";
    }
}
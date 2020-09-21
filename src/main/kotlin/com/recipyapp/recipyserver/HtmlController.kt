package com.recipyapp.recipyserver

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {
    @GetMapping("/")
    fun recipy(model: Model): String {
        model["title"] = "Blog"
        return "blog";
    }
}
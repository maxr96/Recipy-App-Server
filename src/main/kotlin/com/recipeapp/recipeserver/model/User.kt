package com.recipeapp.recipeserver.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column
        var username: String = "",

        @Column
        var email: String = "",

        @Column
        var password: String = ""
)
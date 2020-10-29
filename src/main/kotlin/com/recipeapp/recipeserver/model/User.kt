package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class User (
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column(unique = true)
        var username: String = "",

        @Column(unique = true)
        var email: String = "",

        @Column
        var password: String = "",

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(name = "user_roles",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")])
        var roles: MutableSet<Role> = mutableSetOf(Role())
)
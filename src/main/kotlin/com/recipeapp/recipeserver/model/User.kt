package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
@Table(
    name = "Users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"], name = "uniqueUserNameConstraint"),
        UniqueConstraint(columnNames = ["email"], name = "uniqueEmailConstraint")
    ]
)
class User(
    @Id
    @GeneratedValue
    @Column
    var id: Int = 0,

    @Column
    var username: String = "",

    @Column
    var email: String = "",

    @Column
    var password: String = "",

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf(Role())
)

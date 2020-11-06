package com.recipeapp.recipeserver.model

import javax.persistence.*


@Entity
@Table(name = "Roles")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    var name: RoleName = RoleName.USER
)
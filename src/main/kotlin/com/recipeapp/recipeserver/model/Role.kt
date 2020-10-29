package com.recipeapp.recipeserver.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*


@Entity
@Table(name = "roles")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private var name: RoleName = RoleName.USER
)
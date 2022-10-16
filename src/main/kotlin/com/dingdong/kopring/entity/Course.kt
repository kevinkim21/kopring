package com.dingdong.kopring.entity

import javax.persistence.*

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    var category: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTORS", nullable = false)
    val instructor: Instructor? = null,
)
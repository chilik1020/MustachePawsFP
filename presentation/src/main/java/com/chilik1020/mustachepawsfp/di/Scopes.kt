package com.chilik1020.mustachepawsfp.di

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppScope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class LoginScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class SignUpScope
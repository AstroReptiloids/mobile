package com.example.myapplication.di

interface IHasComponent<C> {

    fun getComponent(): C
}
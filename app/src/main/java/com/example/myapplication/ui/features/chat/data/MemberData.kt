package com.example.myapplication.ui.features.chat.data

class MemberData(var name: String?, var color: String?) {

    override fun toString(): String {
        return "MemberData{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}'
    }
}
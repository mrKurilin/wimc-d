package com.mrkurilin.wimc_d.data.model.destinations

data class Destination(val name: String = "") {

    override fun toString(): String {
        return name
    }
}
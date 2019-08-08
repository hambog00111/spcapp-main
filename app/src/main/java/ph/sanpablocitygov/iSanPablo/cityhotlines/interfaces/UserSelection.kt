package com.hpdeveloper.kotlinsample.interfaces

import com.firebase.ui.auth.data.model.User
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlineModel


interface UserSelection {

    fun onUserSelected(cityhotlineModel : CityhotlineModel)
}
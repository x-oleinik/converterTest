package io.realmagic.convertertest

interface MainContract {

    interface View{
        fun startAnimate()
        fun stopAnimate()
        fun showBottomSheet()
    }

    interface Presenter{
        fun getRates()
        fun countRate()
    }
}
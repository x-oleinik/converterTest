package io.realmagic.convertertest

interface MainContract {

    interface View{
        fun startAnimate()
        fun stopAnimate()
        fun showBottomSheet(isFrom : Boolean, list : ArrayList<String>)
        fun updateAmount(amount : String)
        fun errorDialog(errorText : String)
    }

    interface Presenter{
        fun getList(isFrom: Boolean)
        fun getRatesToDb()
        fun countFromBase(from: String, to: String, amount : Double)
    }
}
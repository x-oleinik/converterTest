package io.realmagic.convertertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realmagic.convertertest.UI.BottomSheetCurr
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, this)
        presenter.getRates()

        from.setOnClickListener {
            showBottomSheet()
        }
        to.setOnClickListener {
            showBottomSheet()
        }

    }

    override fun startAnimate() {
        tempView.visibility = View.VISIBLE
        tempView.animate().alpha(0.5f).start()
        progress.visibility = View.VISIBLE    }

    override fun stopAnimate() {
        tempView.animate().alpha(0f).start()
        tempView.visibility = View.GONE
        progress.visibility = View.GONE    }

    override fun showBottomSheet() {
        val bottomSheet = BottomSheetCurr()
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

}

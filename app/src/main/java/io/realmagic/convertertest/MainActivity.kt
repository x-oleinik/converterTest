package io.realmagic.convertertest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import io.realmagic.convertertest.UI.BottomSheetCurr
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_l.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, this)

        presenter.getRatesToDb()

        textListeners()
        clickHandlers()


    }


    override fun startAnimate() {
        tempView.visibility = View.VISIBLE
        tempView.animate().alpha(0.5f).start()
        progress.visibility = View.VISIBLE    }

    override fun stopAnimate() {
        tempView.animate().alpha(0f).start()
        tempView.visibility = View.GONE
        progress.visibility = View.GONE    }

    override fun showBottomSheet(isFrom : Boolean, list : ArrayList<String>) {
        val bottomSheet = BottomSheetCurr(isFrom, list)
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

    override fun updateAmount(amount : String){
        amount_tv.text = amount
    }

    override fun errorDialog(errorText : String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string._error_title))
            .setMessage(errorText)
            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun clickHandlers(){
        //from
        from.setOnClickListener {
            presenter.getList(true)
        }
        //to
        to.setOnClickListener {
            presenter.getList(false)
        }
        //count button
        count_butt.setOnClickListener {
            if (input_field.text.isNullOrEmpty()) errorDialog(getString(R.string.empty_error))
            else presenter.countFromBase(from.text.toString(), to.text.toString(), input_field.text.toString().toDouble())//presenter.countRate(from.text.toString(), to.text.toString(), input_field.text.toString().toDouble())
        }
        //swap button
        swap.setOnClickListener {
            val fromText = from.text.toString()
            val toText = to.text.toString()
            from.text = toText
            to.text = fromText
        }
    }

    private fun textListeners(){
        //from
        from.afterTextChanged {
            if (!input_field.text.isNullOrEmpty()) presenter.countFromBase(it, to.text.toString(), input_field.text.toString().toDouble())//presenter.countRate(it, toText, input_field.text.toString().toDouble())
        }
        //to
        to.afterTextChanged {
            if (!input_field.text.isNullOrEmpty()) presenter.countFromBase(from.text.toString(), it, input_field.text.toString().toDouble())//presenter.countRate(fromText, it, input_field.text.toString().toDouble())
        }
        //input
        input_field.afterTextChanged {
            if (!input_field.text.isNullOrEmpty()) presenter.countFromBase(from.text.toString(), to.text.toString(), it.toDouble())//presenter.countRate(from.text.toString(), to.text.toString(), it.toDouble())
            else amount_tv.text = getString(R.string._00_00)
        }
    }

    //Extensions
    fun TextView.afterTextChanged(afterTextChanged: (String) -> Unit){
        this.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                afterTextChanged.invoke(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    fun TextInputEditText.afterTextChanged(afterTextChanged : (String) -> Unit){
        this.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                afterTextChanged.invoke(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

}

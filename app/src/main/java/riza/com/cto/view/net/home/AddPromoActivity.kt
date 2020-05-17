package riza.com.cto.view.net.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_promo.*
import org.jetbrains.anko.intentFor
import riza.com.cto.R
import riza.com.cto.data.db.Area
import riza.com.cto.model.PromoRequest
import riza.com.cto.support.Adapter
import riza.com.cto.support.DatePickerFragment
import riza.com.cto.support.debugLog
import riza.com.cto.view.local.testarea.AreaVH
import riza.com.cto.view.net.selectarea.SelectAreaActivity
import java.util.*

/**
 * Created by riza@deliv.co.id on 5/18/20.
 */

class AddPromoActivity : AppCompatActivity() {

    companion object {
        const val REQ_ADD_AREA = 100
    }

    private val datePicker by lazy { DatePickerFragment() }
    private val mPromo by lazy { PromoRequest() }
    private val mAreas = arrayListOf<Area>()

    private val areaAdapter by lazy {
        object : Adapter<Area, AreaVH>(
            R.layout.item_area,
            AreaVH::class.java,
            Area::class.java,
            mAreas
        ) {
            override fun bindView(holder: AreaVH, data: Area?, position: Int) {
                holder.bind(data = data, onClick = {

                }, onDelete = {
                    mAreas.removeAt(position)
                    notifyDataSetChanged()
                })
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_promo)

        initView()

    }

    private fun initView() {

        rv_area?.adapter = areaAdapter

        btn_add_area?.setOnClickListener {
            startActivityForResult(
                intentFor<SelectAreaActivity>(), REQ_ADD_AREA
            )
        }

        btn_date1?.setOnClickListener {

            datePicker.setup(Calendar.getInstance()) { _, y, m, d ->
                val cal = Calendar.getInstance().apply {
                    set(y, m, d)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }

                mPromo.startDate = cal.timeInMillis
            }
            datePicker.show(supportFragmentManager, "start")
        }

        btn_date2?.setOnClickListener {

            datePicker.setup(Calendar.getInstance()) { _, y, m, d ->
                val cal = Calendar.getInstance().apply {
                    set(y, m, d)
                    set(Calendar.HOUR_OF_DAY, 23)
                    set(Calendar.MINUTE, 59)
                    set(Calendar.SECOND, 59)
                }

                mPromo.endDate = cal.timeInMillis
            }
            datePicker.show(supportFragmentManager, "end")

        }

        btn_confirm?.setOnClickListener {


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (resultCode != Activity.RESULT_OK) return

        when (requestCode) {


            REQ_ADD_AREA -> {

                data?.getParcelableExtra<Area>("data")?.let {

                    debugLog(it)

                    mAreas.forEach { area: Area ->
                        if (it.id == area.id) return@let
                    }

                    mAreas.add(it)
                    areaAdapter.notifyDataSetChanged()
                }

            }

        }

    }
}
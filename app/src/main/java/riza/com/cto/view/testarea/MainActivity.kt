package riza.com.cto.view.testarea

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import riza.com.cto.R
import riza.com.cto.data.db.Area
import riza.com.cto.support.Adapter2
import riza.com.cto.view.check.CheckActivity
import riza.com.cto.view.maps.MapsActivity
import riza.com.cto.view.selectarea.SelectAreaVM

class MainActivity : AppCompatActivity() {


    private lateinit var areaAdapter: Adapter2<Area, AreaVH>
    private val vm by lazy { ViewModelProvider(this).get(SelectAreaVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initObserver()
    }

    private fun initObserver() {

        vm.areas.observe(this, Observer {
            areaAdapter.updateList(it)
        })

    }

    private fun initView() {

        areaAdapter = object : Adapter2<Area, AreaVH>(
            R.layout.item_area,
            AreaVH::class.java,
            Area::class.java,
            emptyList()
        ) {
            override fun bindView(holder: AreaVH, data: Area?, position: Int) {

                holder.bind(
                    data = data,
                    onClick = {

                        startActivity(
                            intentFor<CheckActivity>(
                                "area" to it
                            )
                        )

                    },
                    onDelete = {

                        alert {
                            message = "Hapus ${it.name}?"
                            positiveButton("Ya") { d: DialogInterface ->
                                vm.deleteArea(it)
                                d.dismiss()
                            }

                            negativeButton("Batal") { d: DialogInterface ->
                                d.dismiss()
                            }

                        }.show()

                    }
                )

            }

        }

        rv_area?.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = areaAdapter

        }

        fab_add?.setOnClickListener {
            startActivity(
                intentFor<MapsActivity>()
            )
        }


    }
}

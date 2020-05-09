package riza.com.cto.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_check.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_promo.*
import kotlinx.android.synthetic.main.view_card_stats.*
import riza.com.cto.R
import riza.com.cto.model.Promo
import riza.com.cto.support.Adapter2

/**
 * Created by riza@deliv.co.id on 5/8/20.
 */

class HomeActivity : AppCompatActivity() {


    private val vm by lazy { ViewModelProvider(this).get(HomeVM::class.java) }
    private lateinit var promoAdapter: Adapter2<Promo, PromoVH>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        initObserver()

        vm.getAllPromo()
    }

    private fun initObserver() {

        vm.promos.observe(this, Observer {
            promoAdapter.updateList(it)
        })

        vm.totalArea.observe(this, Observer {
            tv_area?.text = it.toString()
        })

        vm.totalUser.observe(this, Observer {
            tv_n_user?.text = it.toString()
        })

        vm.totalPromo.observe(this, Observer {
            tv_total_promo?.text = it.toString()
        })

    }

    private fun initView() {

        promoAdapter = object : Adapter2<Promo, PromoVH>(
            R.layout.item_promo,
            PromoVH::class.java,
            Promo::class.java,
            emptyList()
        ) {
            override fun bindView(holder: PromoVH, data: Promo?, position: Int) {
                holder.bind(data) {
                    //onclick

                }
            }

        }

        rv_promo?.adapter = promoAdapter


    }

}
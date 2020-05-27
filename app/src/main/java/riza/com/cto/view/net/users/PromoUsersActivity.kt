package riza.com.cto.view.net.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_users_look.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import riza.com.cto.R
import riza.com.cto.model.UserIds
import riza.com.cto.support.Adapter2

/**
 * Created by riza@deliv.co.id on 5/27/20.
 */

class PromoUsersActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context?, promoCode: String, users: List<UserIds>) =
            context?.run {
                intentFor<PromoUsersActivity>().apply {
                    putExtra("code", promoCode)
                    putParcelableArrayListExtra("users", ArrayList(users))
                }.clearTop()
            }


    }

    private val userAdapter by lazy {

        object : Adapter2<UserIds, UserVH>(
            R.layout.item_user,
            UserVH::class.java,
            UserIds::class.java,
            emptyList()
        ) {
            override fun bindView(holder: UserVH, data: UserIds?, position: Int) {
                holder.bind(data) {


                }
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_look)
        initViews()
        initExtras()
    }

    @SuppressLint("SetTextI18n")
    private fun initExtras() {

        intent.getStringExtra("code")?.let {
            tv_title?.text = "Promo #$it"
        }

        intent.getParcelableArrayListExtra<UserIds>("users")?.let {
            userAdapter.updateList(it)
        }

    }

    private fun initViews() {

        btn_back?.setOnClickListener { onBackPressed() }

        rv_user?.adapter = userAdapter

    }


}
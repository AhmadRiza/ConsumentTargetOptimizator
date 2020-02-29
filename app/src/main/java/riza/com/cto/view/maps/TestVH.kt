package riza.com.cto.view.maps

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import riza.com.cto.model.TestModel

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

class TestVH(itemView: View) : RecyclerView.ViewHolder(itemView){


    fun bind(
        data: TestModel,
        listener:(data: TestModel)->Unit
    ){


        itemView.rootView.setOnClickListener {

            listener.invoke(data)

        }


    }



}
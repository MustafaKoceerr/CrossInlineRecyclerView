package com.example.crossinlinerecyclerview

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListViewHolder(itemView: View) : ViewHolder(itemView) {
    val txtName: LinearLayout
    val txtView: TextView

    init {
        txtName = itemView.findViewById(R.id.lnrRootView)
        txtView = itemView.findViewById(R.id.rowTxt)
    }

    fun bind2(onClick: (View) -> Unit) {
        txtName.setOnClickListener(onClick)
    }

    /**
     *      public void setOnClickListener(@Nullable OnClickListener l) {
     *
     * setOnClickListener bizden nesne bekleyen düz bir fonksiyon,
     *
     * Biliyoruz ki inline kullanımı nesne oluşturmayı kaldırıp, fonksiyonun body'si içindeymiş gibi davranıyor.
     *
     * için inline fonksiyondan kullanılamaz
     *   txtView.setOnClickListener {
     *             return
     *         }
     *         kendisi desteklemiyor
     */

    inline fun bind(string: String, crossinline onClick: (TextView) -> Unit) {
        txtView.text = string
        txtView.setOnClickListener {
            onClick.invoke(txtView)
        }
    }
}
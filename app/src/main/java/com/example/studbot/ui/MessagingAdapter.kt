package com.example.studbot.ui

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.studbot.R
import com.example.studbot.data.Message
import com.example.studbot.utils.Constants.RECEIVE_ID
import com.example.studbot.utils.Constants.SEND_ID
import kotlinx.android.synthetic.main.message_item.view.*
import kotlinx.android.synthetic.main.toast.*
import java.util.*


class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()
    inner class MessageViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        init {
            itemview.setOnClickListener{
                //todo
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {


        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false))
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        val context = holder.itemView.context
        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }

                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                if(currentMessage.message.contains("alarm"))
                {
                    holder.itemView.la.visibility = View.VISIBLE
                    //holder.itemView.la1.visibility= View.GONE

                    val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val intent = Intent(context,MyAlarm::class.java)
                    val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)

                    holder.itemView.alarmbtn.setOnClickListener{

                        val calendar: Calendar = Calendar.getInstance()
                        if (Build.VERSION.SDK_INT >= 23) {
                            calendar.set(
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH),
                                holder.itemView.timepicker.hour,
                                holder.itemView.timepicker.minute,
                                0
                            )
                        } else {
                            calendar.set(
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH),
                                holder.itemView.timepicker.currentHour,
                                holder.itemView.timepicker.currentMinute, 0
                            )
                        }
                        //setAlarm(calendar.timeInMillis,context)

                        alarm.setExact(
                            AlarmManager.RTC_WAKEUP,
                            calendar.timeInMillis,
                            pendingIntent)
                        Toast.makeText(context,"Alarm is set",Toast.LENGTH_LONG).show()

                    }
                    holder.itemView.cancelbtn.setOnClickListener {
                        alarm.cancel(pendingIntent)
                        Toast.makeText(context,"Alarm Cancelled",Toast.LENGTH_LONG).show()
                    }
                }
                else if (currentMessage.message.contains("rate"))
                {

                   // holder.itemView.la1.visibility= View.GONE
                    holder.itemView.la2.visibility= View.VISIBLE
//                    if( holder.itemView.rbar.rating > 0 )
//                    {
//                        holder.itemView.t1.visibility = View.VISIBLE
//                    }
//                    else
//                    {
//                        holder.itemView.t1.visibility = View.GONE
//                    }

                }

                else if (currentMessage.message.contains("progress"))
                {
                    holder.itemView.la1.visibility= View.VISIBLE
                    //holder.itemView.la.visibility= View.GONE
                    val progress = holder.itemView.progr_et.text.toString()
                    if(progress.isNotEmpty())
                    {
                        val pro= progress.toInt()
                        //print(pro)
                        holder.itemView.progress.setProgress(pro)
                        //ObjectAnimator.ofInt(holder.itemView.progress,"progress",45)
                            //.setDuration(2000)
                            //.start()
                    }
                }

                holder.itemView.tv_message.visibility = View.GONE
            }
        }
        //currentMessage.message

    }
//
//    private fun setAlarm(timeInMillis: Long,context: Context) {
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(this, MyAlarm::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        alarmManager.setRepeating(
//            AlarmManager.RTC,
//            timeInMillis,
//            AlarmManager.INTERVAL_DAY,
//            pendingIntent
//        )
//        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
//
//    }


    override fun getItemCount(): Int {

        return messagesList.size
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return position
    }
    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }



}
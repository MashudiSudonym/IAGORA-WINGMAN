package com.iagora.wingman.receive_order.features.main_features.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.iagora.wingman.receive_order.features.main_features.R
import com.iagora.wingman.receive_order.features.main_features.ReceiveOrderActivity
import com.iagora.wingman.receive_order.features.main_features.ReceiveOrderActivity.Companion.KEY_DATA_NOTIFY
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.core.mapper.MapperReceiveOrder.toModel
import org.json.JSONObject
import kotlin.random.Random


class FirebaseNotificationService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        var pendingIntent: PendingIntent? = null
        val notificationID = Random.nextInt()
        var messageTitle: String? = ""
        var messageBody: String? = ""
        var messageImageUrl: String? = ""


        //  Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            messageTitle = remoteMessage.notification!!.title
            messageBody = remoteMessage.notification!!.body
            messageImageUrl = remoteMessage.notification!!.imageUrl.toString()
        }

        Log.e("REMOTE_MESSAGE", remoteMessage.toString())


        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            if (remoteMessage.data[TYPE] == NEW_ORDER) {
                try {
                    val notification = remoteMessage.data[DETAILS]

                    val intent =
                        Intent(this, ReceiveOrderActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            putExtra(
                                KEY_DATA_NOTIFY,
                                Gson().fromJson(
                                    notification,
                                    ReceiveOrderBody::class.java
                                ).toModel()
                            )
                        }

                    startActivity(intent)


//                    val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6288261323037&text=Hallo Wingman"))
//                    startActivity(intent2)
//                    TriggerWhatsApp.openWhatsApp(
//                        "6288261323037",
//                        "Hallo Wingman, saya bisa tawar belanjaan saya pada nomor order ?",
//                        this.packageManager,
//                        this
//                    )

                    Log.e("NOTIFY", notification.toString())
                } catch (e: Throwable) {
                    Log.e("ERROR_SET", e.toString())
                }
            }

            Log.e("CEKDDATAAAA", "Message data: " + remoteMessage.data[DETAILS])
        }


        if (messageBody != null && messageTitle != null) {
            showNotification(messageTitle, messageBody, messageImageUrl, remoteMessage.data)
        }
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification(
        title: String?,
        messageBody: String?,
        imageUrl: String?,
        data: Map<String, String>? = null
    ) {


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        var pendingIntent: PendingIntent? = null
        val intent = Intent(this, ReceiveOrderActivity::class.java)

        val notification = data?.get(NOTIFICATION)
        val dataNotification = data?.get(CUSTOMER_DATA)
        val json = JSONObject(notification as String)

//        val soundUri: Uri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.sound_notification)
        val soundUriEcek =
            Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.sound)


        // create notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(mChannel)
        }

        pendingIntent = if (data[TYPE] == NEW_ORDER) {
            dataNotification?.let { dataNotify -> openReceiveOrder(notificationID, dataNotify) }
        } else {
            PendingIntent.getActivity(
                this,
                notificationID /* Request code */,
                intent,
                PendingIntent.FLAG_ONE_SHOT
            )
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)


        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_close)
            .setColor(ContextCompat.getColor(applicationContext, R.color.redPrimary))
            .setContentTitle(json.getString("title"))
            .setContentText(json.getString("body"))
            .setAutoCancel(true)
            .setOngoing(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setLargeIcon(imageNotifyToBitmap(json.getString(IMAGE))) // untuk icon gambar samping kanan
            .setStyle(
                if (json.getString(IMAGE).length > 4) NotificationCompat.BigPictureStyle()
                    .bigPicture(imageNotifyToBitmap(json.getString(IMAGE)))
                else null
            ) // untuk gambar besar di body

        notificationManager.notify(notificationID, notificationBuilder.build())

    }


    private fun imageNotifyToBitmap(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null

        try {
            val futureTarget = Glide
                .with(this)
                .asBitmap()
                .load(imageUrl)
                .submit()
            bitmap = futureTarget.get()
        } catch (e: Exception) {
        }

        return bitmap
    }


    // Open Receive Order Activity
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun openReceiveOrder(notificationID: Int, data: String): PendingIntent {
        val intent = Intent(applicationContext, ReceiveOrderActivity::class.java).apply {
            putExtra(
                KEY_DATA_NOTIFY,
                Gson().fromJson(
                    data,
                    ReceiveOrderBody::class.java
                ).toModel()
            )
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return PendingIntent.getActivity(
            this,
            notificationID /* Request code */,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
    }

    companion object {
        const val ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469
        const val CHANNEL_ID = "push-notification-Iagora-Wingman"
        const val CHANNEL_NAME = "Iagora-Wingman-Notification"
        const val TYPE = "type"
        const val NEW_ORDER = "new-order"
        const val NOTIFICATION = "notification"
        const val CUSTOMER_DATA = "costumerdata"
        const val IMAGE = "image"
        const val DETAILS = "details"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        println("NEWTOKENGENERATE $token")
    }
}
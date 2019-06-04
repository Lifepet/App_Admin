package com.example.app_admin.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.app_admin.MainActivity
import com.example.app_admin.R
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.activity_volunteer_write.*
import kotlinx.android.synthetic.main.activity_volunteer_write.edit_title
import kotlinx.android.synthetic.main.activity_volunteer_write.imageView4
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class VolunteerWriteActivity : AppCompatActivity() {

    private val PICK_FROM_ALBUM = 1
    var imageUrl: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_write)

        imageView4.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, PICK_FROM_ALBUM)
        }

        bt_write.setOnClickListener { postVolunteer() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_FROM_ALBUM) {
            val photoUri = data!!.data
            var cursor: Cursor? = null
            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                assert(photoUri != null)
                cursor = contentResolver.query(photoUri!!, proj, null, null, null)
                assert(cursor != null)
                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                imageUrl = File(cursor.getString(column_index))

            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
            Log.d("test", imageUrl.toString())
            setImage()

        }
    }

    fun setImage() {
        val options = BitmapFactory.Options()
        val originalBm = BitmapFactory.decodeFile(imageUrl!!.getAbsolutePath(), options)
        imageView4.setImageBitmap(originalBm)

    }

    fun postVolunteer() {
        val file = File(imageUrl?.toURI())
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val title = RequestBody.create(MediaType.parse("text/plane"), edit_title.text.toString())
        val content = RequestBody.create(MediaType.parse("text/plane"), edit_content.text.toString())
        val sex = RequestBody.create(MediaType.parse("text/plane"), "남")
        val age = RequestBody.create(MediaType.parse("text/plane"), edit_age.text.toString())
        val kind = RequestBody.create(MediaType.parse("text/plane"), edit_type.text.toString())
        val start = RequestBody.create(MediaType.parse("text/plane"), edit_start.text.toString())
        val end = RequestBody.create(MediaType.parse("text/plane"), edit_end.text.toString())
        val fileName = MultipartBody.Part.createFormData("image", imageUrl!!.name, requestBody)
        Connecter.api.postVolunteer(getToken(applicationContext), title, sex, content, kind, start, end, age, fileName)
            .enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.code() == 201) {
                        startActivity<MainActivity>()
                    }
                }

            })
    }
}

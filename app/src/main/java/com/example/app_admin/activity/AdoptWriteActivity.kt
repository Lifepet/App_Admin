package com.example.app_admin.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import com.example.app_admin.R
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.activity_adopt_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.find
import java.io.File

class AdoptWriteActivity : AppCompatActivity() {

    private val PICK_FROM_ALBUM = 1
    var imageUrl: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adopt_write)
        imageView4.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, PICK_FROM_ALBUM)
        }
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

    fun postAdopt(){
        val requestBody = RequestBody.create(MediaType.parse("image/*"), imageUrl)
//        val title = RequestBody.create(MediaType.parse("text/plane"), reviewTitle.value.toString())
//        val content = RequestBody.create(MediaType.parse("text/plane"), reviewContent.value.toString())
//        val fileName = MultipartBody.Part.createFormData("image", file.name, requestBody)
//        Connecter.api.postAdopt(getToken(applicationContext),)
    }
}

package com.abhitom.pokemonclassifier

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    val PICK_IMAGE=1
    var uri:Uri?=null
    var imgData:Byte?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivPokemon.setOnClickListener {
            var gallery=Intent()
            gallery.setType("image/*")
            gallery.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==PICK_IMAGE && resultCode== Activity.RESULT_OK){
            uri=data?.data
            var bitmap=MediaStore.Images.Media.getBitmap(contentResolver,uri)
            ivPokemon.setImageBitmap(bitmap)
        }
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap?):ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}
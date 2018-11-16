package jp.paming.listsample.gallary

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatActivity.onCreatePhotoPermission(grant:()->Unit){
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            Toast.makeText(this, "パーミッションがOFFになっています。", Toast.LENGTH_SHORT).show();

        } else {

            // No explanation needed, we can request the permission.
            val permission = Manifest.permission.READ_EXTERNAL_STORAGE
            ActivityCompat.requestPermissions(this,
                arrayOf(permission),
                12345)

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }else {
        grant()
    }
}

fun AppCompatActivity.read(): List<PhotoData> {

    val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} desc"

    val cursor = contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null,
        null,
        null,
        sortOrder
    ) ?: return emptyList()
    //        cursor.moveToFirst()

    Log.d("column","${cursor.columnCount}")
    for( i in (0 until cursor.columnCount) ){
        Log.d("column_name","${cursor.getColumnName(i)}")
    }
    Log.d("count", "${cursor.count}")

    val ret = ArrayList<PhotoData>()
    if (cursor.moveToFirst()){
        do {
            val date = {
                val addedSec = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))
                Date(addedSec * 1000)
            }()
            val bmpUri = {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
            }()
            val lat = cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE))
            val lon = cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE))
            val loc = when{
                lat!=0.0 && lon!=0.0 -> Location(lat,lon)
                else -> null
            }
            ret.add(PhotoData(bmpUri,date,loc))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return ret
}

data class Location(val lat:Double, val lon:Double)

class PhotoData(val uri: Uri, val date: Date, val loc:Location?){
    private val df = SimpleDateFormat("yyyy/MM/dd HH:mm")

    var dateString:String = ""
        get() = df.format(date)

}

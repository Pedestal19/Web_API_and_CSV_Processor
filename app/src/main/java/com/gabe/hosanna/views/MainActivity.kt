

package com.gabe.hosanna.views

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Status
import com.gabe.hosanna.R
import com.gabe.hosanna.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    var REQUEST_CODE_WRITE_STORAGE_PERMISION = 105

    internal val URL15 = "https://drive.google.com/u/0/uc?id=1giBv3pK6qbOPo0Y02H-wjT9ULPksfBCm&export=download"


    lateinit var buttonStartDownload: Button

    lateinit var textViewProgressFifteen: TextView


    lateinit var progressBarFifteen: ProgressBar


    internal var downloadIdFifteen: Int = 0

    lateinit var buttonContinue: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        dirPath = Utils.getRootDirPath(applicationContext)
        //        Toast.makeText(getApplicationContext(), "this is pth "+dirPath, Toast.LENGTH_SHORT);
        Log.e("tag", "this is pth " + dirPath!!)



        init()


        onClickListenerDownload()

        onClickListenerContinue()
    }

    private fun init() {

        buttonStartDownload = findViewById(R.id.buttonCancelFifteen)

        textViewProgressFifteen = findViewById(R.id.textViewProgressFifteen)


        progressBarFifteen = findViewById(R.id.progressBarFifteen)

        buttonContinue = findViewById(R.id.buttonContinue)

        checkStoragePermissions(this)

        val file = File(Environment.getExternalStorageDirectory().toString() + "/" + "decagon/car_owners_data.csv")

        var fileExists = file.exists()

        if(fileExists){
            print("csv does exist.")
            buttonFifteen.isEnabled = false
            buttonStartDownload.isEnabled = false
            buttonFifteen.setText(R.string.completed)
            buttonContinue.visibility = View.VISIBLE
            textViewProgressFifteen2.text = "File Already Downloaded"
            textViewProgressFifteen.text = "Saved to Internal Storage/Decagon"
            buttonFifteen.visibility = View.INVISIBLE
            buttonCancelFifteen.visibility = View.INVISIBLE

        } else {
            print("csv does not exist.")
        }

    }



    fun onClickListenerContinue() {
        buttonContinue.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this, HomeActivity::class.java))
        })
    }

    fun onClickListenerDownload() {
        buttonFifteen.setOnClickListener(View.OnClickListener {
            textViewProgressFifteen2.text="Downloading File..."

            if (Status.RUNNING == PRDownloader.getStatus(downloadIdFifteen)) {
                PRDownloader.pause(downloadIdFifteen)
                return@OnClickListener
            }

            buttonFifteen.isEnabled = false
            progressBarFifteen.isIndeterminate = true
            progressBarFifteen.indeterminateDrawable.setColorFilter(
                    Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN)

            if (Status.PAUSED == PRDownloader.getStatus(downloadIdFifteen)) {
                PRDownloader.resume(downloadIdFifteen)
                return@OnClickListener
            }
            downloadIdFifteen = PRDownloader.download(URL15, dirPath, "car_owners_data.csv")
                    .build()
                    .setOnStartOrResumeListener {
                        progressBarFifteen.isIndeterminate = false
                        buttonFifteen.isEnabled = true
                        buttonFifteen.setText(R.string.pause)
                        buttonStartDownload.isEnabled = true
                        buttonStartDownload.setText(R.string.cancel)
                    }
                    .setOnPauseListener { buttonFifteen.setText(R.string.resume) }
                    .setOnCancelListener {
                        downloadIdFifteen = 0
                        buttonFifteen.setText(R.string.start)
                        buttonStartDownload.isEnabled = false
                        progressBarFifteen.progress = 0
                        textViewProgressFifteen.text = ""
                        progressBarFifteen.isIndeterminate = false
                    }
                    .setOnProgressListener { progress ->
                        val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                        progressBarFifteen.progress = progressPercent.toInt()
                        textViewProgressFifteen.text = Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes)
                    }
                    .start(object : OnDownloadListener {
                        override fun onDownloadComplete() {
                            buttonFifteen.isEnabled = false
                            buttonStartDownload.isEnabled = false
                            buttonFifteen.setText(R.string.completed)
                            buttonFifteen.visibility = View.INVISIBLE
                            buttonCancel.visibility = View.INVISIBLE

                            buttonContinue.visibility = View.VISIBLE
                            textViewProgressFifteen2.text="File Download Complete!"
                            textViewProgressFifteen.text="Saved to -> InternalStorage/Decagon"
                            buttonCancelFifteen.visibility = View.INVISIBLE

                        }

                        override fun onError(error: Error) {
                            buttonFifteen.setText(R.string.start)
                            Toast.makeText(applicationContext, getString(R.string.some_error_occurred) + " " + "15", Toast.LENGTH_SHORT).show()
                            textViewProgressFifteen.text = ""
                            progressBarFifteen.progress = 0
                            downloadIdFifteen = 0
                            buttonStartDownload.isEnabled = false
                            progressBarFifteen.isIndeterminate = false
                            buttonFifteen.isEnabled = true
//                            Log.e("errorr", error.serverErrorMessage)
                        }
                    })
        })

        buttonStartDownload.setOnClickListener { PRDownloader.cancel(downloadIdFifteen) }
    }

    companion object {

        private var dirPath: String? = null
    }

    private fun checkStoragePermissions(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_CODE_WRITE_STORAGE_PERMISION)
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_CODE_WRITE_STORAGE_PERMISION)
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_STORAGE_PERMISION)
                    }
                }
            } else {
                val folder = File(Environment.getExternalStorageDirectory().toString() + "/" + "decagon")
                if (!folder.exists()) {
                    folder.mkdirs()
                }
                dirPath = folder.absolutePath
                //        Toast.makeText(getApplicationContext(), "this is pth "+dirPath, Toast.LENGTH_SHORT);
                Log.e("tag", "this is pth " + dirPath!!)

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_STORAGE_PERMISION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val folder = File(Environment.getExternalStorageDirectory().toString() + "/" + "decagon")
                    if (!folder.exists()) {
                        folder.mkdirs()
                    }
                    dirPath = folder.absolutePath
                    //        Toast.makeText(getApplicationContext(), "this is pth "+dirPath, Toast.LENGTH_SHORT);
                    Log.e("tag", "this is pth " + dirPath!!)
                }
            }
        }

    }




}


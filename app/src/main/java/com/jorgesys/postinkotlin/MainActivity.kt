package com.jorgesys.postinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.doAsync
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {

    private lateinit var tvResponse: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResponse = findViewById(R.id.textViewConsole)
       val btnGet = findViewById(R.id.btnGet) as Button
       val btnPost = findViewById(R.id.btnPost) as Button

        btnGet.setOnClickListener {view ->
            doAsync {
              sendGet();
            }
        }


        btnPost.setOnClickListener {view ->
            doAsync {
              sendPost();
            }
        }

    }


    private fun sendGet() {
        val url = "https://postman-echo.com/get?Country=Romania&City=Iasi"
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("\nSending 'GET' request to URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("GET Response : " + response.toString())
                tvResponse.setText("GET Response : " + response.toString())
            }
        }
    }


    fun sendPost() {

        var urlPost = "https://postman-echo.com/post";
        var msgPost = "Jorgesys was here! :-) !"

        var reqParam = URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(msgPost, "UTF-8")
        val mURL = URL(urlPost)

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "POST"

            val wr = OutputStreamWriter(getOutputStream());
            wr.write(reqParam);
            wr.flush();

            println("\nSending 'POST' request to URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("POST Response : $response")
                tvResponse.setText("POST Response : $response")
            }
        }
    }



}

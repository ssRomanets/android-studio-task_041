package com.example.task_041

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.MediaController
import com.example.task_041.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val videoList = mutableListOf(
        R.raw.magistr0, R.raw.magistr1, R.raw.magistr2, R.raw.magistr3, R.raw.magistr4,
    )

    private var videoDataIndex = 2
    private var pauseVideoAct = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(mediaController)
        binding.videoView.setMediaController(mediaController)

        binding.playFAB.setOnClickListener{
            val offlineUri = Uri.parse("android.resource://$packageName/${videoList[videoDataIndex]}")
            binding.videoView.setVideoURI(offlineUri)
            binding.videoView.requestFocus()
            binding.videoView.start()
        }
        binding.pauseFAB.setOnClickListener{
            if (pauseVideoAct == false)
            {
                pauseVideoAct = true
                binding.videoView.pause()
            }
            else
            {
                pauseVideoAct = false;
                binding.videoView.start()
            }
        }
        binding.stopFAB.setOnClickListener{
            binding.videoView.stopPlayback()
        }

        binding.nextFAB.setOnClickListener{
            if (videoDataIndex +1 <= videoList.size-1)
            {
                ++videoDataIndex
                pauseVideoAct = false;
                binding.videoView.stopPlayback()
            }
        }

        binding.prevFAB.setOnClickListener{
            if (videoDataIndex -1 >= 0)
            {
                --videoDataIndex
                pauseVideoAct = false;
                binding.videoView.stopPlayback()
            }
        }
    }
}
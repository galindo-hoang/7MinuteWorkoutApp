package com.example.a7minuteworkout

import android.app.Dialog
import android.content.ContentResolver
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityDialogBinding
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var setupBreakTime:Long = 5
    private var setupExerciseTime:Long = 4
    private var tts: TextToSpeech? = null
    private var mediaPlayer: MediaPlayer? = null


    private var binding: ActivityExerciseBinding? = null

    private var start: CountDownTimer? = null
    private var startTime:Long = setupBreakTime

    private var exercise: CountDownTimer? = null
    private var exerciseTime:Long = setupExerciseTime

    private var exerciseList: ArrayList<Exercise>? = null
    private var currentExercise = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)


        setSupportActionBar(this.binding!!.actionBar)
        // show arrow left
        if(supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.binding!!.actionBar.setNavigationOnClickListener {
            setupNavigationBar()
        }


        mediaPlayer = MediaPlayer.create(this, Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + File.separator + packageName + "/" + R.raw.press_start))
        mediaPlayer?.isLooping = false


        binding?.pbCountDownStart?.max = startTime.toInt()
        binding?.pbCountDownExercise?.max = exerciseTime.toInt()


        exerciseList = Constants.getExerciseList()
        tts = TextToSpeech(this,this)


        binding?.rcvExerciseList?.adapter = ExerciseViewAdapter(exerciseList!!)
        binding?.rcvExerciseList?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        this.setupBreakPB()

    }

    override fun onBackPressed() {
        this.setupNavigationBar()
    }

    private fun setupNavigationBar() {
        val check = when{
            (start != null) -> 0
            else -> 1
        }

        if(check == 0) start!!.cancel()
        else exercise!!.cancel()


        val dialog = Dialog(this)
        val bindingDialog = ActivityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDialog.root)
        dialog.setCanceledOnTouchOutside(false)

        bindingDialog.btnYes.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        bindingDialog.btnNo.setOnClickListener {
            dialog.dismiss()
            if(check == 0) setupBreakPB()
            else setupExercisePB()
        }


        dialog.show()
    }


    fun setupBreakPB(){
        exercise = null
        binding?.imExercise?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flCircleExercise?.visibility = View.INVISIBLE

        binding?.tvUpComingExercise?.text = exerciseList!![currentExercise+1].getName()
        binding?.tvUpComingExercise?.visibility = View.VISIBLE
        binding?.flCircleStart?.visibility = View.VISIBLE
        binding?.tvStart?.visibility = View.VISIBLE


        mediaPlayer?.start()
        tts?.speak("Exercise coming up is ${binding?.tvUpComingExercise?.text}",TextToSpeech.QUEUE_FLUSH,null,null)
        this.startCountDown()
    }


    private fun startCountDown(){
        start = object: CountDownTimer(startTime*1000,1000){
            override fun onTick(p0: Long) {
                --startTime
                binding?.tvTimerStart?.text = startTime.toString()
                binding?.pbCountDownStart?.progress = startTime.toInt()
            }

            override fun onFinish() {
                ++currentExercise
                startTime = setupBreakTime
                if(currentExercise < exerciseList!!.size){
                    exerciseList!![currentExercise].setIsSelected(true)
                    binding?.rcvExerciseList?.adapter?.notifyItemChanged(currentExercise)
                }
                this@ExerciseActivity.setupExercisePB()
            }

        }.start()
    }


    fun setupExercisePB(){
        start = null
        binding?.flCircleStart?.visibility = View.INVISIBLE
        binding?.tvStart?.visibility = View.INVISIBLE
        binding?.tvUpComingExercise?.visibility = View.INVISIBLE

        binding?.imExercise?.setImageResource(exerciseList!![currentExercise].getImage())
        binding?.imExercise?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flCircleExercise?.visibility = View.VISIBLE

        binding?.tvExerciseName?.text = exerciseList!![currentExercise].getName()
        exerciseTime = setupExerciseTime
        this.exerciseCountDown()
    }


    private fun exerciseCountDown(){
        exercise = object: CountDownTimer(exerciseTime*1000,1000){
            override fun onTick(p0: Long) {
                --exerciseTime
                binding?.tvTimerExercise?.text = exerciseTime.toString()
                binding?.pbCountDownExercise?.progress = exerciseTime.toInt()
            }

            override fun onFinish() {
                exerciseList!![currentExercise].setIsSelected(false)
                exerciseList!![currentExercise].setIsFinish(true)
                binding?.rcvExerciseList?.adapter?.notifyItemChanged(currentExercise)

                if(currentExercise < (exerciseList!!.size - 1)){
                    this@ExerciseActivity.setupBreakPB()
                }else{
                    finish()
                    startActivity(Intent(this@ExerciseActivity,FinishActivity::class.java))
                }
            }

        }.start()
    }

    override fun onDestroy() {
        if(start != null){
            start?.cancel()
            start = null
        }
        if(exercise != null){
            exercise?.cancel()
            exercise = null
        }
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(mediaPlayer != null){
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }

        super.onDestroy()
    }

    override fun onInit(p0: Int) {
        if(TextToSpeech.SUCCESS == p0){
            tts!!.language = Locale.ENGLISH
            if(TextToSpeech.LANG_MISSING_DATA == tts!!.isLanguageAvailable(Locale.ENGLISH) || TextToSpeech.LANG_NOT_SUPPORTED == tts!!.isLanguageAvailable(Locale.ENGLISH)){
                Log.w("Warning","Language not Supported")
            }
        }else Log.e("Error","Text to Speech not Available")
    }

}
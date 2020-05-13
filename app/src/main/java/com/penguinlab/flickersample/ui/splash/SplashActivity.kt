package com.penguinlab.flickersample.ui.splash

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.ActivitySplashBinding
import com.penguinlab.flickersample.ui.MainActivity
import com.penguinlab.flickersample.ui.base.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {
    override val viewModelClass: Class<SplashActivityViewModel> =
        SplashActivityViewModel::class.java
    override val layoutRes: Int = R.layout.activity_splash

    @Inject
    lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    private val animationStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchRemoteConfigParameters()
    }

    private fun fetchRemoteConfigParameters() {
        viewModel.fetchRemoteConfigParameters()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        if (!hasFocus || animationStarted) {
            return
        }
        animate()
        super.onWindowFocusChanged(hasFocus)
    }

    private fun animate() {
        val container = findViewById<View>(R.id.container) as ViewGroup
        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            val viewAnimator: ViewPropertyAnimatorCompat = ViewCompat.animate(v)
                .setStartDelay(STARTUP_DELAY.toLong())
                .alphaBy(0f)
                .alpha(1f)
                .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                    DecelerateInterpolator(1.2f)
                )
            viewAnimator.setInterpolator(DecelerateInterpolator())
                .setListener(object : ViewPropertyAnimatorListener {
                    override fun onAnimationEnd(view: View?) {
//                        this@SplashActivity.moveForward()
                        showForceUpdateDialog()
                    }

                    override fun onAnimationCancel(view: View?) {
                    }

                    override fun onAnimationStart(view: View?) {
                    }

                }).start()
        }
    }

    companion object {
        const val STARTUP_DELAY = 500
        const val ANIM_ITEM_DURATION = 2500
    }

    private fun moveForward() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }


    fun showForceUpdateDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(
            ContextThemeWrapper(
                this,
                android.R.style.Theme_DeviceDefault_Dialog_Alert
            )
        )
        alertDialogBuilder.setTitle("context.getString(R.string.youAreNotUpdatedTitle)")
        alertDialogBuilder.setMessage(
            ""
        )
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            android.R.string.ok,
            DialogInterface.OnClickListener { dialog, id ->
                this.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + this.packageName)
                    )
                )
                dialog.cancel()
            })
        alertDialogBuilder.show()
    }
}
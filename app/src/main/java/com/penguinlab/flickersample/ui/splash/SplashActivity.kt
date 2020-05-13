package com.penguinlab.flickersample.ui.splash

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.penguinlab.common.observeNonNull
import com.penguinlab.common.ui.Util
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

    private var animationStarted = false

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
                        viewModel.remoteModel_.observeNonNull(this@SplashActivity) { remoteModel ->
                            if (Util.CheckNewVersionAvailable(remoteModel.versionOnGooglePlay)) {
                                showForceUpdateDialog(
                                    this@SplashActivity,
                                    remoteModel.isForceUpdateRequired
                                )
                            } else {
                                this@SplashActivity.moveForward()
                            }

                        }
                        animationStarted = true
                    }

                    override fun onAnimationCancel(view: View?) {
                    }

                    override fun onAnimationStart(view: View?) {
                        animationStarted = true
                    }

                }).start()
        }
    }

    private fun moveForward() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

    private fun showForceUpdateDialog(context: Context, isForceUpdateRequired: Boolean) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(context.getString(R.string.info))
            .setMessage(context.getString(R.string.new_version_of_app_available))
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { dialog, id ->
                this.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + this.packageName)
                    )
                )
                dialog.cancel()
            }
        if (!isForceUpdateRequired) {
            alertDialog.setNegativeButton(android.R.string.cancel) { dialog, id ->
                this@SplashActivity.moveForward()
                dialog.cancel()
            }
        }
        alertDialog.show()
    }

    companion object {
        const val STARTUP_DELAY = 500
        const val ANIM_ITEM_DURATION = 2500
    }

}
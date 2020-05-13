package com.penguinlab.flickersample.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.penguinlab.common.ForceUpdateDef
import com.penguinlab.common.ForceUpdateModel
import com.penguinlab.common.RxAwareViewModel
import com.penguinlab.common.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject


class SplashActivityViewModel @Inject constructor(private val firebaseRemoteConfig: FirebaseRemoteConfig) :
    RxAwareViewModel() {


    private val remoteModel = MutableLiveData<ForceUpdateModel>()
    val remoteModel_: LiveData<ForceUpdateModel> = remoteModel

    private val remoteConfigParameters = Observable.create<ForceUpdateModel> { emitter ->
        firebaseRemoteConfig.fetch(ForceUpdateDef.getCacheExpiration())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("remote config is fetched.")
                    val model = ForceUpdateModel(
                        isForceUpdateRequired = firebaseRemoteConfig.getBoolean(ForceUpdateDef.KEY_FORCE_UPDATE_REQUIRED),
                        versionOnGooglePlay = firebaseRemoteConfig.getString(ForceUpdateDef.KEY_CURRENT_VERSION),
                        googlePlayUrl = firebaseRemoteConfig.getString(ForceUpdateDef.KEY_STORE_URL)
                    )
                    firebaseRemoteConfig.fetchAndActivate()
                    emitter.onNext(model)
                } else {
                    emitter.onError(task.exception?.cause!!)
                }
            }

    }


    fun fetchRemoteConfigParameters(): Disposable? {
        return remoteConfigParameters
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ forceUpdateModel ->
                Timber.d("forceUpdateModel result ${forceUpdateModel.versionOnGooglePlay} ")
                remoteModel.value = forceUpdateModel
            }, {
                Timber.e(it)
            })
            .also { disposable += it }
    }
}

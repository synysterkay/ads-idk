package com.example.andmoduleads.activity.reward

import android.app.Activity
import android.widget.Toast
import com.ads.control.ads.AperoAd
import com.ads.control.ads.AperoAdCallback
import com.ads.control.ads.wrapper.ApAdError
import com.ads.control.ads.wrapper.ApRewardAd
import com.ads.control.billing.AppPurchase

object RewardAdsManage {
    private val rewardMap = mutableMapOf<Pair<String, String>, ApRewardAd?>()
    private val rewardStateMap = mutableMapOf<Pair<String, String>, RewardState>()

    fun showReward(
        activity: Activity,
        rewardIdPriority: String,
        rewardIdDefault: String,
        reload: Boolean,
        userActionCallback: () -> Unit
    ) {
        if (AppPurchase.getInstance().isPurchased) {
            userActionCallback()
            return
        }
        val onNextCallback = {
            rewardMap[Pair(rewardIdPriority, rewardIdDefault)] = null
            userActionCallback()
            if (reload) {
                loadReward(activity, rewardIdPriority, rewardIdDefault)
            }
        }
        val rewardAd = rewardMap[Pair(rewardIdPriority, rewardIdDefault)]
        if (rewardAd != null && rewardAd.isReady) {
            AperoAd.getInstance().forceShowRewardAd(
                activity,
                rewardAd,
                object : AperoAdCallback() {
                    override fun onAdImpression() {
                        super.onAdImpression()
                        if (AperoAd.getInstance().isShowMessageTester) {
                            Toast.makeText(
                                activity,
                                "Show reward ad ${rewardAd.admobReward?.adUnitId}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onNextAction() {
                        super.onNextAction()
                        onNextCallback()
                    }

                    override fun onAdFailedToShow(adError: ApAdError?) {
                        super.onAdFailedToShow(adError)
                        onNextCallback()
                    }
                }
            )
        } else {
            onNextCallback()
        }
    }

    fun loadReward(
        activity: Activity,
        rewardIdPriority: String,
        rewardIdDefault: String
    ) {
        val canRequestAd = canRequestAd(rewardIdPriority, rewardIdDefault)
        if (!canRequestAd) {
            return
        }
        rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] = RewardState.LOADING
        var rewardAd: ApRewardAd? = null
        if (rewardIdPriority.isEmpty() && rewardIdDefault.isEmpty()) {
            rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] = RewardState.FINISHED
            return
        }

        if (rewardIdPriority.isEmpty() && rewardIdDefault.isNotEmpty()) {
            rewardAd = loadIdDefaultOrPriorityReward(activity, rewardIdDefault, rewardIdPriority)
            return
        }

        rewardAd = AperoAd.getInstance().getRewardAd(
            activity,
            rewardIdPriority,
            object : AperoAdCallback() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    if (AperoAd.getInstance().isShowMessageTester) {
                        Toast.makeText(
                            activity,
                            "Load reward ad ${rewardAd?.admobReward?.adUnitId}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    rewardMap[Pair(rewardIdPriority, rewardIdDefault)] = rewardAd!!
                    rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] = RewardState.FINISHED
                }

                override fun onAdFailedToLoad(adError: ApAdError?) {
                    super.onAdFailedToLoad(adError)
                    if (rewardIdDefault.isNotEmpty()) {
                        rewardAd = loadIdDefaultOrPriorityReward(activity, rewardIdDefault, rewardIdPriority)
                    } else {
                        rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] = RewardState.FINISHED
                    }
                }
            }
        )

    }

    private fun loadIdDefaultOrPriorityReward(
        activity: Activity,
        rewardIdDefault: String,
        rewardIdPriority: String,
    ): ApRewardAd? {
        var rewardAd: ApRewardAd? = null
        rewardAd = AperoAd.getInstance().getRewardAd(
            activity,
            rewardIdDefault,
            object : AperoAdCallback() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    rewardMap[Pair(rewardIdPriority, rewardIdDefault)] = rewardAd!!
                    rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] =
                        RewardState.FINISHED
                    if (AperoAd.getInstance().isShowMessageTester) {
                        Toast.makeText(
                            activity,
                            "Load reward ad ${rewardAd?.admobReward?.adUnitId}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onAdFailedToLoad(adError: ApAdError?) {
                    super.onAdFailedToLoad(adError)
                    rewardMap[Pair(rewardIdPriority, rewardIdDefault)] = null
                    rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] =
                        RewardState.FINISHED
                }
            }
        )
        return rewardAd
    }

    private fun canRequestAd(rewardIdPriority: String, rewardIdDefault: String): Boolean {
        val purchased = AppPurchase.getInstance().isPurchased
        val apRewardAd = rewardMap[Pair(rewardIdPriority, rewardIdDefault)]
        val isRequesting = rewardStateMap[Pair(rewardIdPriority, rewardIdDefault)] == RewardState.LOADING
        val isAdReady = apRewardAd != null && apRewardAd.isReady
        return when {
            purchased || isRequesting || isAdReady -> {
                false
            }

            else -> {
                true
            }
        }
    }
}

private enum class RewardState {
    UNDEFINED, LOADING, FINISHED
}
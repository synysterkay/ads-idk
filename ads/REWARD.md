# Ad Reward
### About Reward:
- Ad Reward (Rewarded Ads) in Android is a form of interactive advertising where users can earn rewards (such as points, in-game currency, or special features) after completing an ad viewing. To integrate AdMob Rewarded Ads in your Android app using Kotlin, 
- You can follow these steps: Load Reward, Show Ads

### About Param:
  - activity : Activity :
  - rewardIdPriority : String :
  - rewardIdDefault : String :
  - callback:() -> Unit : 
  - AperoAd.getInstance().isShowMessageTester : Boolean : Show ID ADS to test when ads start
# 1. Show ads

* 1.1 Reward Single

    ~~~
    fun showReward(
        activity : Activity, 
        rewardIdPriority : String, 
        rewardIdDefault : String, 
        callback:() -> Unit )
    {
        AperoAd.getInstance().forceShowRewardAd(
                activity, 
                rewardAd, 
                object :AperoAdCallback() {
                    override fun onAdImpression() {
                        super.onAdImpression() if (AperoAd.getInstance().isShowMessageTester) {
                            Toast.makeText(activity, "Show reward ad ${rewardAd.admobReward?.adUnitId}", Toast.LENGTH_SHORT).show()
                        }
                    /** CALL WHEN SHOW SUSCESS ADS REWARD */
                    }
            
                    override fun onNextAction() {
                        super.onNextAction() 
                        callback()
                    /** CALL WHEN ADS SHOW FINISH */
                    }
            
                    override fun onAdFailedToShow(adError:ApAdError ?){
                        super.onAdFailedToShow(adError) 
                        callback()
                    /** CALL WHEN ADS SHOW FAILD */
                    }
                }   
        )
    }
    ~~~
  
* 1.2 Reward Multiple

    ~~~
    fun showReward(
        activity : Activity, 
        rewardIdPriority : String, 
        rewardIdDefault : String, 
        callback:() -> Unit )
    {
        AperoAd.getInstance().forceShowRewardAd(
                    activity,
                    rewardAd, ( --- REPLACE BY rewardAd : RewardAd ---- )
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
                            /** CALL WHEN SHOW SUSCESS ADS REWARD */
                        }
    
                        override fun onNextAction() {
                            super.onNextAction()
                            callback()
                            /** CALL WHEN ADS SHOW FINISH */
                        }
    
                        override fun onAdFailedToShow(adError: ApAdError?) {
                            super.onAdFailedToShow(adError)
                            callback()
                            /** CALL WHEN ADS SHOW FAILD */
                        }
                    }
                )
        
    }
    ~~~
# 2. Load ads

* 2.1 Reward Single

     ~~~
         fun loadReward(
             activity : Activity,
             rewardIdPriority : String,
             rewardIdPriority : String
         ){{
            AperoAd.getInstance().getRewardAd(
                activity,
                REWARD_ID, ( --- REPLACE BY rewardIdPriority , rewardIdPriority ---) 
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
                        /** LOAD SUCCESS, SAVE REWARD ADS TO REWARD */
                }
                    override fun onAdFailedToLoad(adError: ApAdError?) {
                        super.onAdFailedToLoad(adError)
                        /** FAILD TO LOAD ADS, MAYBE LOAD AGAIN IN THIS */
                    }
                }
         )
     ~~~
  
* 2.1 Reward Multiple

     ~~~
        fun loadReward(
           activity : Activity,
           rewardIdPriority : String,
           rewardIdDefault : String
        ){
            AperoAd.getInstance().getRewardAd(
                activity,
                REWARD_ID, ( ----REPLACE BY rewardIdPriority , rewardIdPriority ----)
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
                       /** LOAD SUCCESS, SAVE REWARD ADS TO REWARD 
                        RECOMMENT USING MAP TO MANAGE TO REWARD MULTIPLE */
                }
  
                    override fun onAdFailedToLoad(adError: ApAdError?) {
                        super.onAdFailedToLoad(adError)
                        /** FAILD TO LOAD ADS, MAYBE LOAD AGAIN IN THIS */
                    }
                }
        )
     ~~~


    

        

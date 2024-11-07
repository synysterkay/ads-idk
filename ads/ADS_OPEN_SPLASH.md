# AppOpenAd Implement Guide

## AppOpenAd integration

**Initializing AppOpenAd**

- In the App class, add the AppOpenAd ids:
    ```bash
    override fun onCreate() {
        ...
        aperoAdConfig.idAdResume = "YOUR_NORMAL_AD_ID"
        aperoAdConfig.idAdResumeMedium = "YOUR_MEDIUM_ADS_ID"
        aperoAdConfig.idAdResumeHigh = "YOUR_HIGH_ADS_ID"
        ...
    }
    ```

**Controlling AppOpenAd Behavior**

- Disable ads for specific activities:
    ```bash
    AppOpenManager.getInstance().disableAppResumeWithActivity(YourActivity::class.java)
    ```

- Skip displaying the AppOpenAd once:
    ```bash
    AppOpenManager.getInstance().disableAdResumeByClickAction()
    ```

## Splash AppOpenAd Integration

**Loading and Displaying Splash AppOpenAd**

- To load and display the Splash AppOpenAd:

    ```bash
    val appOpenResult = AppOpenAdManager.loadAppOpenAd(activity, "YOUR_AD_ID") 
    ```
- If you need to load AppOpenAd with high Id:
    ```bash
    val appOpenResult = AppOpenAdManager.loadAppOpenAdHighFloor(activity, "YOUR_HIGH_AD_ID", "YOUR_NORMAL_AD_ID")
    ```
> Note: `appOpenResult` can be null when failed to load.

- To display AppOpenAd, follow this:
    ```bash
    AppOpenAdManager.showAppOpenAd(
        activity = activity,
        appOpenResult = appOpenResult,      // If appOpenResult is null you should perform the next action
        onNextAction = onNextAction,        // Called when ad is closed
        onAdClick = onAdClick,              // Called when ad is clicked
        onAdImpression = onAdImpression,    // Called when ad is displayed
    )
    ```
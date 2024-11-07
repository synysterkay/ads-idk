#  Ads interstitial
# Interstitial Ad Manager

This library provides functions to manage and display Interstitial ads in your Android application.

## Functions

### Load ads
``` kotlin
loadInterstitialAdHighFloor(context: Context, adUnitPriorityId: String, adUnitDefaultId: String): ApInterstitialAd?
```
- **Description**: Loads an Interstitial ad with high priority. If the high-priority ad (`adUnitPriorityId`) fails to load and returns `null`, the default ad (`adUnitDefaultId`) will be used as a fallback. This function runs in the background and returns an `ApInterstitialAd` or `null` if both ad units fail.
- **Parameters**:
    - `context`: A valid `Context` (such as an Activity or Application context) to be used for loading the ad.
    - `adUnitPriorityId`: The ID of the high-priority ad.
    - `adUnitDefaultId`: The ID of the fallback ad if the high-priority ad fails.

``` kotlin
loadInterstitialAd(context: Context, adUnitId: String): ApInterstitialAd?
```

- **Description**: Loads an Interstitial ad using the specified ad ID. This function runs in the background and returns an `ApInterstitialAd` or `null` if loading the ad fails.
- **Parameters**:
    - `context`: A valid `Context` (such as an Activity or Application context) to be used for loading the ad.
    - `adUnitId`: The ID of the ad to load.

### Show ads
``` kotlin
showInterstitialAd(activity: Activity, interstitialAd: ApInterstitialAd?, onAdClicked: () -> Unit, onAdImpression: () -> Unit, onNextAction: () -> Unit)
```
- **Description**: Displays the Interstitial ad. This function will invoke the provided callbacks when the ad is clicked or shown.
- **Parameters**:
    - `activity`: The current Activity.
    - `interstitialAd`: The `ApInterstitialAd` object that has been loaded.
    - `onAdClicked`: Callback invoked when the ad is clicked.
    - `onAdImpression`: Callback invoked when the ad is displayed.
    - `onNextAction`: Callback invoked to perform the next action after the ad is displayed or clicked.

## Example
`The ad loading mechanism will be executed in the screens prior to displaying the interstitial ads.
For instance, I have configured the loading process to occur in the InterstitialsActivityPreload screen.
Once the ad has successfully loaded, we will navigate to the InterstitialsActivity screen, where the interstitial ad will be presented to the user.
`
```kotlin
// Load high-priority ad with a fallback to default ad
val interstitialAdHighFloor = InterstitialAdManager.loadInterstitialAdHighFloor(
    context, 
    adUnitPriorityId, 
    adUnitDefaultId
)

// Load ad
interAdMap[adUnitId] = InterstitialAdManager.loadInterstitialAd(
    context, 
    adUnitId
)

// Show ad
InterstitialAdManager.showInterstitialAd(
    context, 
    interAdMap[adUnitId], 
    onAdClicked = { /* Handle ad click */ },
    onAdImpression = { /* Handle ad impression */ },
    onNextAction = { /* Perform next action */ }
)
```
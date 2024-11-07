
# AperoModuleAds
This is SDK ads by [Apero](https://apero.vn/). It has built in some sdk for easy use like
- Admob
- MAX Mediation(Applovin)
- Google Billing
- Adjust
- Appsflyer
- Facebook SDK
- Firebase auto log tracking event, tROAS

# What types of ad modules are supported?
- [Banner](./ads/BANNER.md)
- [Interstitial](./ads/INTER.md)
- [Native](./ads/NATIVE.md)
- [Native Full Screen](./ads/NATIVE_FULLSCREEN.md)
- [Reward](./ads/REWARD.md)
- [Interstitial Splash](./ads/INTERSTITIAL_SPLASH.md)
- [AppOpenAd](./ads/APP_OPEN_AD.md)
# Import Module
Contact us for account
~~~
    maven {
        url 'https://artifactory.apero.vn/artifactory/gradle-release/'
            credentials {
                username "$username"
                password "$password"
            }
        }
    maven {
        url "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea"
    }
    maven {
        url 'https://artifact.bytedance.com/repository/pangle'
    }

    implementation 'apero-inhouse:apero-ads:7.0.7'
~~~

# [Change Log](https://sites.google.com/apero.vn/aperoreleasenot/ads?authuser=0)

# Summary
* [Setup AperoAd](#setup_aperoad)
  * [Setup id ads](#set_up_ads)
  * [Config ads](#config_ads)
  * [Ads Formats](#ads_formats)

* [Billing App](#billing_app)
* [Ads rule](#ads_rule)

# Setup AperoAd
## Setup enviroment with id ads for project

We recommend you to setup 2 environments for your project, and only use test id during development, ids from your admob only use when needed and for publishing to Google Store
* The name must be the same as the name of the marketing request
* Config variant test and release in gradle
* appDev: using id admob test while dev
* appProd: use ids from your admob,  build release (build file .aab)

~~~    
      productFlavors {
      appDev {
              manifestPlaceholders = [ ad_app_id:"AD_APP_ID_TEST" ]
              buildConfigField "String", "ads_inter_turn_on", "\"AD_ID_INTERSTIAL_TEST\""
              buildConfigField "String", "ads_inter_turn_off", "\"AD_ID_INTERSTIAL_TEST\""
              buildConfigField "Boolean", "build_debug", "true"
           }
       appProd {
            // ADS CONFIG BEGIN (required)
               manifestPlaceholders = [ ad_app_id:"AD_APP_ID" ]
               buildConfigField "String", "ads_inter_splash", "\"AD_ID_INTERSTIAL\""
               buildConfigField "String", "ads_inter_turn_on", "\"AD_ID_INTERSTIAL\""
               buildConfigField "Boolean", "build_debug", "false"
            // ADS CONFIG END (required)
           }
      }
~~~
AndroidManifest.xml
~~~
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="${ad_app_id}" />
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id" />
        <meta-data
            android:name="com.facebook.sdk.AutoInitEnabled"
            android:value="true" />
        <meta-data
            android:name="com.facebook.sdk.AutoLogAppEventsEnabled"
            android:value="true" />
        <meta-data
            android:name="com.facebook.sdk.AdvertiserIDCollectionEnabled"
            android:value="true" />
~~~
* NOTE : do not set applicationId containing ".example" to avoid the case that id ads no fill

## Config ads
Create class Application

Configure your mediation here. using PROVIDER_ADMOB or PROVIDER_MAX

*** Note:
- Don't use id ad test for production environment
- Environment:
  - ENVIRONMENT_DEVELOP: for test ads and billing.
  - ENVIRONMENT_PRODUCTION: for prdouctions ads and billing.
~~~
class App : AdsMultiDexApplication(){
    @Override
    public void onCreate() {
        super.onCreate();
    ...
        String environment = BuildConfig.build_debug ? AperoAdConfig.ENVIRONMENT_DEVELOP : AperoAdConfig.ENVIRONMENT_PRODUCTION;
	aperoAdConfig = new AperoAdConfig(this,API_KEY_ADS, AperoAdConfig.PROVIDER_ADMOB, environment);

        // Optional: setup Adjust event
        AdjustConfig adjustConfig = new AdjustConfig(true,ADJUST_TOKEN);
        adjustConfig.setEventAdImpression(EVENT_AD_IMPRESSION_ADJUST);
        adjustConfig.setEventNamePurchase(EVENT_PURCHASE_ADJUST);
        aperoAdConfig.setAdjustConfig(adjustConfig);

        // Optional: setup Appsflyer event
        AppsflyerConfig appsflyerConfig = new AppsflyerConfig(true,APPSFLYER_TOKEN);
        aperoAdConfig.setAppsflyerConfig(appsflyerConfig);

        // Optional: enable ads resume
        aperoAdConfig.setIdAdResume(BuildConfig.ads_open_app);

        // Optional: setup list device test - recommended to use
        listTestDevice.add(DEVICE_ID_TEST);
        aperoAdConfig.setListDeviceTest(listTestDevice);

        AperoAd.getInstance().init(this, aperoAdConfig, false);

        // Auto disable ad resume after user click ads and back to app
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        // If true -> onNextAction() is called right after Ad Interstitial showed
        Admob.getInstance().setOpenActivityAfterShowInterAds(false);
    }
}
~~~

Use UMP or not(since version 6.x.x)

Follow [Doc CMP](https://sites.google.com/apero.vn/apero-document/document/doc-cmp)
~~~
        if (isEnableUMP()) {
            adsConsentManager = new AdsConsentManager(this);
            adsConsentManager.requestUMP(
                    canRequestAds -> runOnUiThread(this::loadSplash));
        } else {
            AperoAd.getInstance().initAdsNetwork();
            loadSplash();
        }
~~~


# Ads rule
## Always add device test to idTestList with all of your team's device
To ignore invalid ads traffic
https://support.google.com/adsense/answer/16737?hl=en
## Before show full-screen ad (interstitial, app open ad), alway show a short loading dialog
To ignore accident click from user. This feature is existed in library
## Never reload ad on onAdFailedToLoad
To ignore infinite loop

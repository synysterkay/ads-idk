
# Native Ads

**Native ads** are a type of advertising designed to blend seamlessly with the content of the application in which they appear. Instead of displaying as traditional advertisements, **Native ads** often take on a form and function similar to the surrounding content, making it easier for users to engage without feeling disturbed.


## Native Ads Normal

### Load Ads

Load native ads before showing
```kotlin
private fun loadNativeAds(  
    idAds: String,  
    canShowAds: Boolean,  
    canReloadAds: Boolean,  
    @LayoutRes layoutId: Int,  
    context: Context,  
    lifecycleOwner: LifecycleOwner,  
) {  
  
    val nativeAdHelper = NativeAdHelper(  
        context,  
        lifecycleOwner,  
        NativeAdConfig(  
            idAds,  
            canShowAds,  
            canReloadAds,  
            layoutId,  
        ),  
    )  
    nativeAdHelper.requestAds(NativeAdParam.Request.create())  
}
```
Input parameters:
-   **idAds:** The ID of the ad to be loaded.
-   **canShowAds:** This parameter indicates whether the ad can be displayed. If the value is `true`, the ad will be shown; if `false`, it will not be displayed.
-   **canReloadAds:** This parameter determines whether the ad can be reloaded. If the value is `true`, the ad can be reloaded when needed.
-   **layoutId:** The ID of the layout that the ad will use for display, which is a resource ID for the layout in XML.
-   **context:** This parameter represents the current context (activity or application context) for loading ads.
-   **lifecycleOwner:** This parameter links the ads with the lifecycle of a component (such as Activity or Fragment). This is important to ensure that the ads are managed correctly and do not cause memory leaks when the component's lifecycle changes.

Sample layout ads native:
```xml
<?xml version="1.0" encoding="utf-8"?>  
<com.google.android.gms.ads.nativead.NativeAdView 	xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:app="http://schemas.android.com/apk/res-auto"  
  xmlns:tools="http://schemas.android.com/tools"  
  android:layout_width="match_parent"  
  android:layout_height="wrap_content"  
  android:background="@drawable/bg_native_new"  
  android:backgroundTint="#F5F5F5">  
  
	 <androidx.constraintlayout.widget.ConstraintLayout 
		  android:layout_width="match_parent"  
		  android:layout_height="wrap_content"  
		  android:orientation="vertical"  
		  android:paddingBottom="@dimen/_10sdp">  
		  
		 <com.google.android.gms.ads.nativead.MediaView  
			 android:id="@+id/ad_media"  
			  android:layout_width="match_parent"  
			  android:layout_height="200dp"  
			  app:layout_constraintEnd_toEndOf="parent"  
			  app:layout_constraintStart_toStartOf="parent"  
			  app:layout_constraintTop_toBottomOf="@+id/llAdsContent" />  
		  
		 <LinearLayout  
			 android:id="@+id/llAdsContent"  
			  android:layout_width="match_parent"  
			  android:layout_height="wrap_content"  
			  android:orientation="horizontal"  
			  android:padding="@dimen/_10sdp"  
			  app:layout_constraintTop_toTopOf="parent">  
		  
			 <TextView 
				  android:id="@+id/txtAds"  
				  android:layout_width="wrap_content"  
				  android:layout_height="wrap_content"  
				  android:background="@drawable/bg_primary_ads"  
				  android:paddingHorizontal="@dimen/_4sdp"  
				  android:paddingVertical="@dimen/_2sdp"  
				  android:text="Ad"  
				  android:textColor="@color/white"  
				  android:textSize="10sp"  
				  android:textStyle="bold" />  
			  
			 <TextView 
				  android:id="@+id/ad_headline"  
				  android:layout_width="wrap_content"  
				  android:layout_height="wrap_content"  
				  android:layout_marginStart="@dimen/_10sdp"  
				  android:layout_marginBottom="@dimen/_1sdp"  
				  android:ellipsize="end"  
				  android:fontFamily="@font/dm_sans_regular"  
				  android:maxLines="1"  
				  android:textColor="#FF3E3E"  
				  android:textSize="16sp"  
				  android:textStyle="bold"  
				  tools:text="StreamYard is a live streaming studio" />  
		 </LinearLayout>  
		 <TextView 
			  android:id="@+id/ad_body"  
			  android:layout_width="0dp"  
			  android:layout_height="wrap_content"  
			  android:ellipsize="end"  
			  android:maxLength="90"  
			  android:maxLines="2"  
			  android:paddingHorizontal="@dimen/_10sdp"  
			  android:textColor="#666666"  
			  android:textSize="12sp"  
			  app:layout_constraintBottom_toBottomOf="@id/ad_call_to_action"  
			  app:layout_constraintEnd_toStartOf="@id/ad_call_to_action"  
			  app:layout_constraintStart_toStartOf="parent"  
			  app:layout_constraintTop_toTopOf="@id/ad_call_to_action"  
			  tools:text="The easiest way to create professional livestream. Get started today. " />  
		  
		 <androidx.appcompat.widget.AppCompatButton  
			 android:id="@+id/ad_call_to_action"  
			  style="?android:attr/borderlessButtonStyle"  
			  android:layout_width="wrap_content"  
			  android:layout_height="wrap_content"  
			  android:layout_marginHorizontal="@dimen/_8sdp"  
			  android:layout_marginTop="@dimen/_12sdp"  
			  android:background="@drawable/bg_cta_btn_old"  
			  android:fontFamily="@font/dm_sans_bold"  
			  android:paddingHorizontal="@dimen/_15sdp"  
			  android:paddingVertical="@dimen/_12sdp"  
			  android:textColor="#E9132D"  
			  android:textSize="16sp"  
			  app:layout_constraintBottom_toBottomOf="parent"  
			  app:layout_constraintEnd_toEndOf="parent"  
			  app:layout_constraintTop_toBottomOf="@id/ad_media"  
			  tools:text="INSTALL" />  
	 </androidx.constraintlayout.widget.ConstraintLayout>    
</com.google.android.gms.ads.nativead.NativeAdView>
```
### Show Ads
```kotlin
private fun showNativeAds() {  
    nativeAdHelper  
  .setShimmerLayoutView(shimmerContainerNative)  
        .setNativeContentView(containerNative)  
        .registerAdListener(object : AperoAdCallback() {  
            override fun onAdImpression() {  
                super.onAdImpression()  
            }  
  
            override fun onAdLoaded() {  
                super.onAdLoaded()  
            }  
  
            override fun onAdClicked() {  
                super.onAdClicked()  
            }  
  
            override fun onAdFailedToLoad(adError: ApAdError?) {  
                super.onAdFailedToLoad(adError)  
            }  
  
            override fun onAdFailedToShow(adError: ApAdError?) {  
                super.onAdFailedToShow(adError)  
            }  
        })  
}
```
Explanation of callbacks:
-   **onAdClicked()**: Called when the user clicks on the ad. Logic can be added to track or log this click event.
-   **onAdImpression()**: Called when the ad is displayed on the screen. Typically used to track the number of times the ad is viewed.
-   **onAdLoaded()**: Called when the ad has successfully loaded. Actions such as hiding the loading effect and displaying the ad can be performed here.
-   **onAdFailedToLoad(adError: ApAdError?)**: Called when the ad fails to load. Handle the error or notify the user.
-   **onAdFailedToShow(adError: ApAdError?)**: Called when the ad fails to display even though it has successfully loaded. This can be due to several reasons, such as the ad being invalid or blocked.

Sample layout container native ads
```xml
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        ...

        <FrameLayout
            android:id="@+id/layoutAdNative"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="@dimen/_10sdp"
            android:orientation="vertical"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent">

            <include
                android:id="@+id/layoutShimmer"
                layout="@layout/layout_loading_ads_native" />
        </FrameLayout>
    </androidx.constraintlayout.widget.ConstraintLayout>
```
Layout loading ad native
```
The layout is the same as that of the native ad layout but will use ShimmerFrameLayout instead of NativeAdView to create the loading animation
```


## Native Highpriority
**Native Highpriority** is a type of native ad that requires a high minimum price (floor price) to be displayed. This often ensures better ad quality, helping to optimize revenue for app developers and improve user experience by

### Load ads highpriority
Load ads
```kotlin
private fun loadNativeHighPriorityAds(  
    idAds: String,  
    idAdsHighPriority: String,  
    canShowAds: Boolean,  
    canReloadAds: Boolean,  
    @LayoutRes layoutId: Int,  
    activity: Activity,  
    lifecycleOwner: LifecycleOwner,  
) {  
  
    val nativeAdHighPriorityHelper = NativeAdHighFloorHelper(  
        activity,  
        lifecycleOwner,  
        NativeAdHighFloorConfig(  
            idAds,  
            idAdsHighPriority,  
            canShowAds,  
            canReloadAds,  
            layoutId,  
        ),  
    )  
    nativeAdHighPriorityHelper.requestAds(NativeAdParam.Request.create())  
}
```
Input parameters (Similar to normal native ads, with an additional high priority ID):
-   **idAdsHighPriority**: The ID of the native ad with high priority.

### Show ads highpriority

The order of loading and showing native high-priority ads is to load `idAdsHighPriority` first. If it loads successfully, it will be displayed; if not, it will load the normal `idAds` and display that instead.

> The method for showing and callbacks is similar to normal native ads.

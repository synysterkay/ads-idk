# Native Fullscreen
- Ads can be formatted to match app's visual design
- When ads loads, app receives an ad object that contains its assets, and is responsible for displaying them
- Ads will be displayed in full screen
- Follow these steps:

  - Create shimmer layout: `native_fullscreen_shimmer.xml`:
    ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <com.facebook.shimmer.ShimmerFrameLayout 
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:id="@+id/shimmerContainerNative"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/ad_unit_content"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="bottom"
                android:layout_marginHorizontal="@dimen/_12sdp"
                android:layout_marginBottom="@dimen/_16sdp"
                android:background="@color/white"
                android:layoutDirection="ltr"
                android:orientation="vertical"
                android:paddingBottom="@dimen/_8sdp">

                <androidx.appcompat.widget.AppCompatTextView
                    android:id="@+id/iconAd"
                    style="@style/AppTheme.Ads"
                    android:background="@drawable/bg_ad_ads_tl_8_br_8"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent" />

                <androidx.appcompat.widget.AppCompatImageView
                    android:id="@+id/ad_app_icon"
                    android:layout_width="48dp"
                    android:layout_height="48dp"
                    android:layout_marginStart="@dimen/_10sdp"
                    android:adjustViewBounds="true"
                    android:src="@color/lightTransparent"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@id/iconAd" />

                <androidx.appcompat.widget.AppCompatTextView
                    android:id="@+id/ad_headline"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="@dimen/_5sdp"
                    android:layout_marginEnd="@dimen/_10sdp"
                    android:background="@color/lightTransparent"
                    android:ellipsize="end"
                    android:maxLines="2"
                    android:textColor="@color/color_red"
                    android:textSize="14sp"
                    android:textStyle="bold"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintStart_toEndOf="@id/ad_app_icon"
                    app:layout_constraintTop_toTopOf="@id/ad_app_icon" />

                <androidx.appcompat.widget.AppCompatTextView
                    android:id="@+id/ad_body"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="@dimen/_5sdp"
                    android:layout_marginEnd="@dimen/_10sdp"
                    android:background="@color/lightTransparent"
                    android:ellipsize="end"
                    android:lines="2"
                    android:textColor="#797C80"
                    android:textSize="12sp"
                    app:layout_constraintBottom_toBottomOf="@id/ad_app_icon"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintStart_toEndOf="@id/ad_app_icon"
                    app:layout_constraintTop_toBottomOf="@id/ad_headline" />

                <androidx.appcompat.widget.AppCompatButton
                    android:id="@+id/ad_call_to_action"
                    android:layout_width="match_parent"
                    android:layout_height="@dimen/_35sdp"
                    android:layout_marginHorizontal="@dimen/_10sdp"
                    android:layout_marginVertical="@dimen/_10sdp"
                    android:background="@drawable/background_radius_50"
                    android:backgroundTint="@color/lightTransparent"
                    android:gravity="center"
                    android:stateListAnimator="@null"
                    android:textColor="@color/colorWhite"
                    android:textSize="@dimen/_12sdp"
                    android:textStyle="bold"
                    app:layout_constraintTop_toBottomOf="@id/ad_body" />
            </androidx.constraintlayout.widget.ConstraintLayout>

        </com.facebook.shimmer.ShimmerFrameLayout>

    ```

    Preview Native Fullscreen Shimmer Layout:
    
    ![image](https://github.com/user-attachments/assets/043ee4b0-12ba-4415-8b48-2fbc270639e4)

    
  - Create layout xml ad view: `native_fullscreen.xml`

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <FrameLayout
            android:id="@+id/frAds"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <include
                android:id="@+id/includeNativeFullScreen"
                layout="@layout/native_fullscreen_shimmer"
                android:visibility="visible" />
        </FrameLayout>

        <androidx.appcompat.widget.AppCompatImageView
            android:id="@+id/icClose"
            android:layout_width="36dp"
            android:layout_height="36dp"
            android:layout_gravity="right"
            android:layout_margin="24dp"
            android:padding="8dp"
            android:src="@drawable/ic_close"
            app:tint="#80000000" />
    </FrameLayout>
    ```

  - Implement method show in the screen:

    ```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)

        // create native ad config
        val nativeAdConfig = NativeAdConfig(
            idAds = "your_id_ads_fullscreen",
            canShowAds = true,
            canReloadAds = true,
            layoutId = R.layout.native_fullscreen
        )

        // init nativeAdHelper
        val nativeAdHelper = NativeAdHelper(
            context = this,
            lifecycleOwner = this,
            nativeAdConfig = nativeAdConfig
        )

        // can regis registerAdListener
        nativeAdHelper.registerAdListener(object : AperoAdCallback() {
            override fun onAdImpression() {
                super.onAdImpression()
                // this is call back when ads is shown to user
            }

            override fun onAdClicked() {
                super.onAdClicked()
                // this is call back when user click on ads to open ad
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                // this is call back when ads is loaded successfully and ready to show
            }

            override fun onAdClosed() {
                super.onAdClosed()
                // action when ads is closed                  
            }

            override fun onAdFailedToLoad(adError: ApAdError?) {
                super.onAdFailedToLoad(adError)
                // action when ad failed to load
            }
        })

        // this method is used to set the native ad view
        nativeAdHelper.setNativeContentView(binding.frAds)

        // this method is used to set the shimmer layout view if the native ad is loading
        nativeAdHelper.setShimmerLayoutView(binding.includeNativeFullScreen.shimmerContainerNativeFullScreen)

        // this method is used to preload the native ad if the buffer is empty
        // if ads be preloaded, it will not load again
        nativeAdHelper.requestAds(NativeAdParam.Request.create())
    }
    ```
     `idAds: String`: is your ad unit id, value is fixed, not change.

    `canShowAds: Boolean`: is a flag to show or hide ads.

    `canReloadAds: Boolean`: is a flag to reload ads.

    `layoutId: Int`: is a layout id of native ad view.

- Preview native fullscreen:
    - Loading ads:
      
      ![image](https://github.com/user-attachments/assets/f313276e-6817-4f1d-8b4f-98058ff3aae6)

    - Ads loaded:

      ![image](https://github.com/user-attachments/assets/bee7cb00-56aa-495d-a6e7-310ab0fd721e)

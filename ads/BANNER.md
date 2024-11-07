# Ad Banner
## InitBannerAdHelper

```bash
    private fun initBannerAd(): BannerAdHelper {
        val config = BannerAdConfig(
            BuildConfig.banner_normal,
            canShowAds,
            canReloadAd,
        )
        return BannerAdHelper(activity = this, lifecycleOwner = this, config = config)
    }
```

## OnCreate()
Set layout view when init binding successfully

```bash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bannerAdHelper.setBannerContentView(binding.bannerAdView)
    }
```

## RequestAd()
Request 1 ad banner new | banner visible

```bash
	bannerAdHelper.requestAds(BannerAdParam..create())
```

## Show 1 banner new (previously loaded) | banner visible

```bash
    bannerAdHelper.requestAds(BannerAdParam.Ready(adView))
```

## Display ad (loaded) when clickable after milis | not working when call function cancel() | active
khi call again Request or Ready

```bash
    bannerAdHelper.requestAds(BannerAdParam.Clickable(remoteAds.minimumTimeKeepAdsDisplay))
```

## CancelAd()
Cancel progress request ad và hide banner | banner gone

```bash
    bannerAdHelper.cancel()
```

## Ad Callback

```bash
    private val callback = object : AdCallback() {
        override fun onAdClicked() {
            super.onAdClicked()
            // when click banner
        }

        override fun onAdImpression() {
            super.onAdImpression()
            // when view banner
        }
    }
```

## Register ad callback

```bash
    bannerAdHelper.registerAdListener(adCallback)
```

Unregister ad callback


```bash
    bannerAdHelper.unregisterAdListener(adCallback)
```

## Layout container ad banner

```bash
    <FrameLayout
            android:id="@+id/bannerAdView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent">
            <include layout="@layout/layout_banner_control" />
        </FrameLayout>

```
## Inline banner in Activity/Fragment
inlineStyle:
- Admob.BANNER_INLINE_SMALL_STYLE: for small inline banner
- Admob.BANNER_INLINE_LARGE_STYLE: for large inline banner

```bash
    Admob.getInstance().loadInlineBanner(activity, idBanner, inlineStyle, adCallback);
    or
    Admob.getInstance().loadInlineBannerFragment(final Activity activity, String id, final View rootView, String inlineStyle);

```
## Collapsible banner in Activity/Fragment
gravity:

* AppConstant.TOP: banner anchor at the top of layout
* AppConstant.BOTTOM: banner anchor at the bottom of layout

```bash
    enableAutoReload = true
    collapsibleGravity = AppConstant.CollapsibleGravity.BOTTOM
```
### Banner priority
```bash
   fun initBannerHelperPriority() : BannerAdHelper{
       val bannerAdConfig = BannerAd2FloorConfig(
                    idAds = idAds,
                    idAds2Floor = idAdsPriority,
                    canShowAds = canShowAds,
                    canReloadAds = canReloadAd
          )
        return BannerAdHelper(activity = this, lifecycleOwner = this, config = config)
   }

```
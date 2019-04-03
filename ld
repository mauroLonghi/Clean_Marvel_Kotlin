[33mcommit 9cac7000d1c41666ca9b793e5532d51360fcff71[m[33m ([m[1;36mHEAD -> [m[1;32mnew_service[m[33m, [m[1;31morigin/new_service[m[33m)[m
Author: mauroLonghi <mauro.longhi@globant.com>
Date:   Mon Apr 1 12:24:33 2019 -0300

    add carview on recycler and fragment views

[1mdiff --git a/.idea/assetWizardSettings.xml b/.idea/assetWizardSettings.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..2ecf203[m
[1m--- /dev/null[m
[1m+++ b/.idea/assetWizardSettings.xml[m
[36m@@ -0,0 +1,47 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="WizardSettings">[m
[32m+[m[32m    <option name="children">[m
[32m+[m[32m      <map>[m
[32m+[m[32m        <entry key="vectorWizard">[m
[32m+[m[32m          <value>[m
[32m+[m[32m            <PersistentState>[m
[32m+[m[32m              <option name="children">[m
[32m+[m[32m                <map>[m
[32m+[m[32m                  <entry key="vectorAssetStep">[m
[32m+[m[32m                    <value>[m
[32m+[m[32m                      <PersistentState>[m
[32m+[m[32m                        <option name="children">[m
[32m+[m[32m                          <map>[m
[32m+[m[32m                            <entry key="clipartAsset">[m
[32m+[m[32m                              <value>[m
[32m+[m[32m                                <PersistentState>[m
[32m+[m[32m                                  <option name="values">[m
[32m+[m[32m                                    <map>[m
[32m+[m[32m                                      <entry key="url" value="jar:file:/C:/Program%20Files/Android/Android%20Studio/plugins/android/lib/android.jar!/images/material_design_icons/action/ic_autorenew_black_24dp.xml" />[m
[32m+[m[32m                                    </map>[m
[32m+[m[32m                                  </option>[m
[32m+[m[32m                                </PersistentState>[m
[32m+[m[32m                              </value>[m
[32m+[m[32m                            </entry>[m
[32m+[m[32m                          </map>[m
[32m+[m[32m                        </option>[m
[32m+[m[32m                        <option name="values">[m
[32m+[m[32m                          <map>[m
[32m+[m[32m                            <entry key="color" value="ffffff" />[m
[32m+[m[32m                            <entry key="outputName" value="ic_autorenew_black_24dp" />[m
[32m+[m[32m                            <entry key="sourceFile" value="C:\Users\mauro.longhi" />[m
[32m+[m[32m                          </map>[m
[32m+[m[32m                        </option>[m
[32m+[m[32m                      </PersistentState>[m
[32m+[m[32m                    </value>[m
[32m+[m[32m                  </entry>[m
[32m+[m[32m                </map>[m
[32m+[m[32m              </option>[m
[32m+[m[32m            </PersistentState>[m
[32m+[m[32m          </value>[m
[32m+[m[32m        </entry>[m
[32m+[m[32m      </map>[m
[32m+[m[32m    </option>[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/caches/build_file_checksums.ser b/.idea/caches/build_file_checksums.ser[m
[1mindex 0099a02..7c0a2a2 100644[m
Binary files a/.idea/caches/build_file_checksums.ser and b/.idea/caches/build_file_checksums.ser differ
[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex 0322dba..6e7c851 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -1,7 +1,7 @@[m
 apply plugin: 'com.android.application'[m
 apply plugin: 'kotlin-android'[m
 apply plugin: 'kotlin-android-extensions'[m
[31m-[m
[32m+[m[32mapply plugin: 'realm-android'[m
 android {[m
     compileSdkVersion 26[m
     defaultConfig {[m
[36m@@ -12,6 +12,7 @@[m [mandroid {[m
         versionName "1.0"[m
         testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"[m
     }[m
[32m+[m
     buildTypes {[m
         debug {[m
             buildConfigField "String", "PRIVATE_API_KEY_VALUE", '"5824724f08480d75d54491e26f16ca5e"'[m
[36m@@ -34,9 +35,11 @@[m [mandroid {[m
 dependencies {[m
     implementation fileTree(dir: 'libs', include: ['*.jar'])[m
     implementation 'com.android.support:appcompat-v7:26.1.0'[m
[32m+[m[32m    implementation 'com.android.support:design:26.1.0'[m
     implementation 'com.android.support.constraint:constraint-layout:1.0.2'[m
 [m
     implementation 'com.android.support:recyclerview-v7:26.1.0'[m
[32m+[m[32m    implementation 'com.android.support:cardview-v7:26.1.0'[m
 [m
     implementation 'com.squareup.picasso:picasso:2.5.2'[m
     implementation 'com.github.bumptech.glide:glide:3.7.0'[m
[36m@@ -56,7 +59,6 @@[m [mdependencies {[m
 [m
 [m
 }[m
[31m-[m
 android.testOptions.unitTests.all {[m
     // Configure whether failing tests should fail the build[m
     ignoreFailures false[m
[36m@@ -68,3 +70,11 @@[m [mandroid.testOptions.unitTests.all {[m
 repositories {[m
     mavenCentral()[m
 }[m
[32m+[m[32mbuildscript {[m
[32m+[m[32m    repositories {[m
[32m+[m[32m        jcenter()[m
[32m+[m[32m    }[m
[32m+[m[32m    dependencies {[m
[32m+[m[32m        classpath "io.realm:realm-gradle-plugin:5.10.0"[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[1mdiff --git a/app/src/main/java/com/puzzlebench/clean_marvel_kotlin/presentation/MainActivity.kt b/app/src/main/java/com/puzzlebench/clean_marvel_kotlin/presentation/MainActivity.kt[m
[1mindex fc243c4..a0787fb 100644[m
[1m--- a/app/src/main/java/com/puzzlebench/clean_marvel_kotlin/presentation/MainActivity.kt[m
[1m+++ b/app/src/main/java/com/puzzlebench/clean_marvel_kotlin/presentation/MainActivity.kt[m
[36m@@ -7,6 +7,7 @@[m [mimport com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUse[m
 import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity[m
 import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter[m
 import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView[m
[32m+[m[32mimport io.realm.Realm[m
 [m
 open class MainActivity : BaseRxActivity() {[m
 [m
[36m@@ -15,7 +16,10 @@[m [mopen class MainActivity : BaseRxActivity() {[m
 [m
     override fun onCreate(savedInstanceState: Bundle?) {[m
         super.onCreate(savedInstanceState)[m
[31m-        setContentView(R.layout.activity_main)[m
[32m+[m[32m        setContentView(com.puzzlebench.clean_marvel_kotlin.R.layout.activity_main)[m
[32m+[m[32m        Realm.init(this)[m
[32m+[m
[32m+[m[32m        val realm = Realm.getDefaultInstance()[m
         presenter.init()[m
     }[m
 }[m
[1mdiff --git a/app/src/main/res/drawable/ic_autorenew_black_24dp.xml b/app/src/main/res/drawable/ic_autorenew_black_24dp.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..7c05474[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/drawable/ic_autorenew_black_24dp.xml[m
[36m@@ -0,0 +1,5 @@[m
[32m+[m[32m<vector android:height="24dp" android:tint="#FFFFFF"[m
[32m+[m[32m    android:viewportHeight="24.0" android:viewportWidth="24.0"[m
[32m+[m[32m    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">[m
[32m+[m[32m    <path android:fillColor="#FF000000" android:pathData="M12,6v3l4,-4 -4,-4v3c-4.42,0 -8,3.58 -8,8 0,1.57 0.46,3.03 1.24,4.26L6.7,14.8c-0.45,-0.83 -0.7,-1.79 -0.7,-2.8 0,-3.31 2.69,-6 6,-6zM18.76,7.74L17.3,9.2c0.44,0.84 0.7,1.79 0.7,2.8 0,3.31 -2.69,6 -6,6v-3l-4,4 4,4v-3c4.42,0 8,-3.58 8,-8 0,-1.57 -0.46,-3.03 -1.24,-4.26z"/>[m
[32m+[m[32m</vector>[m
[1mdiff --git a/app/src/main/res/layout/activity_main.xml b/app/src/main/res/layout/activity_main.xml[m
[1mindex d8db64b..880aac9 100644[m
[1m--- a/app/src/main/res/layout/activity_main.xml[m
[1m+++ b/app/src/main/res/layout/activity_main.xml[m
[36m@@ -3,7 +3,9 @@[m
     xmlns:tools="http://schemas.android.com/tools"[m
     android:layout_width="match_parent"[m
     android:layout_height="wrap_content"[m
[32m+[m[32m    android:background="@color/colorPrimaryDark"[m
     tools:context=".presentation.MainActivity">[m
[32m+[m
     <ProgressBar[m
         android:id="@+id/progressBar"[m
         style="?android:attr/progressBarStyle"[m
[36m@@ -15,4 +17,12 @@[m
         android:id="@+id/recycleView"[m
         android:layout_width="match_parent"[m
         android:layout_height="match_parent" />[m
[32m+[m
[32m+[m[32m    <android.support.design.widget.FloatingActionButton[m
[32m+[m[32m        android:id="@+id/button_db"[m
[32m+[m[32m        android:layout_width="@dimen/margin_floatingButton"[m
[32m+[m[32m        android:layout_height="@dimen/margin_floatingButton"[m
[32m+[m[32m        android:layout_gravity="bottom|end"[m
[32m+[m[32m        android:background="@color/colorPrimaryDark"[m
[32m+[m[32m        android:src="@drawable/ic_autorenew_black_24dp" />[m
 </FrameLayout>[m
[1mdiff --git a/app/src/main/res/layout/character_cards_layout.xml b/app/src/main/res/layout/character_cards_layout.xml[m
[1mindex 91316cc..23c393e 100644[m
[1m--- a/app/src/main/res/layout/character_cards_layout.xml[m
[1m+++ b/app/src/main/res/layout/character_cards_layout.xml[m
[36m@@ -1,26 +1,37 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
[31m-<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m
[32m+[m[32m    xmlns:card_view="http://schemas.android.com/apk/res-auto"[m
     android:layout_width="match_parent"[m
[31m-    android:layout_height="@dimen/super_hero_row_height">[m
[32m+[m[32m    android:layout_height="@dimen/super_hero_row_height"[m
[32m+[m[32m    android:layout_marginLeft="@dimen/margin_top_card_character"[m
[32m+[m[32m    android:layout_marginTop="@dimen/margin_top_card_character"[m
[32m+[m[32m    android:layout_marginRight="@dimen/margin_top_card_character"[m
[32m+[m[32m    android:layout_marginBottom="@dimen/margin_top_card_character"[m
[32m+[m[32m    card_view:cardElevation="@dimen/cardview_default_elevation">[m
 [m
[31m-    <ImageView[m
[31m-        android:id="@+id/image_thumbnail"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="match_parent" />[m
[32m+[m[32m    <RelativeLayout[m
[32m+[m[32m        android:layout_width="wrap_content"[m
[32m+[m[32m        android:layout_height="wrap_content">[m
 [m
[31m-    <View[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="@dimen/super_hero_row_gradient_height"[m
[31m-        android:layout_alignParentBottom="true"[m
[31m-        android:background="@drawable/super_hero_gradient" />[m
[32m+[m[32m        <ImageView[m
[32m+[m[32m            android:id="@+id/image_thumbnail"[m
[32m+[m[32m            android:layout_width="match_parent"[m
[32m+[m[32m            android:layout_height="match_parent" />[m
 [m
[31m-    <TextView[m
[31m-        android:id="@+id/tv_item"[m
[31m-        android:layout_width="wrap_content"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:layout_alignParentBottom="true"[m
[31m-        android:layout_alignParentLeft="true"[m
[31m-        android:layout_margin="@dimen/default_margin"[m
[31m-        android:textColor="@android:color/white" />[m
[32m+[m[32m        <View[m
[32m+[m[32m            android:layout_width="match_parent"[m
[32m+[m[32m            android:layout_height="@dimen/super_hero_row_gradient_height"[m
[32m+[m[32m            android:layout_alignParentBottom="true"[m
[32m+[m[32m            android:background="@drawable/super_hero_gradient" />[m
 [m
[31m-</RelativeLayout>[m
\ No newline at end of file[m
[32m+[m[32m        <TextView[m
[32m+[m[32m            android:id="@+id/tv_item"[m
[32m+[m[32m            android:layout_width="wrap_content"[m
[32m+[m[32m            android:layout_height="wrap_content"[m
[32m+[m[32m            android:layout_alignParentLeft="true"[m
[32
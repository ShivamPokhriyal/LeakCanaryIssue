# LeakCanaryIssue

Demonstrate `IllegalStateException` when using `LeakCanary#showLeakDisplayActivityLauncherIcon` in robolectric tests. Ref: https://github.com/square/leakcanary/issues/2391


# Project Description

This project is a minimal reproducible sample with following classes

```
- main
  - MyApp.kt (Custom Application class to initialize LeakCanary)
  - LeakCanaryInitializer.kt (An object to enable/disable LeakCanary based on debug toggles. For simplicity, it only enables LeakCanary for now)
  - Util.kt (Sample class with a single method to target unit tests.)
  
- test
  - TestApp.kt (Test Application class which is a subclass of MyApp.kt)
  - UtilTest.kt (Class which runs tests on Util.kt and uses Robolectric)

```


# How to run

Clone the project and run `./gradlew :app:testDebugUnitTest --info`. 
Or after cloning, open the project in AndroidStudio and search for `UtilTest` class and run the test. 

# StackTrace

```

com.example.leakcanaryissue.UtilTest > Validate integer multiplication FAILED
    java.lang.IllegalStateException: LeakCanary not installed, see AppWatcher.manualInstall()
        at leakcanary.internal.InternalLeakCanary.getApplication(InternalLeakCanary.kt:52)
        at leakcanary.internal.InternalLeakCanary.setEnabledBlocking(InternalLeakCanary.kt:324)
        at leakcanary.LeakCanary.showLeakDisplayActivityLauncherIcon(LeakCanary.kt:401)
        at com.example.leakcanaryissue.LeakCanaryInitializer.init(LeakCanaryInitializer.kt:7)
        at com.example.leakcanaryissue.MyApp.onCreate(MyApp.kt:9)
        at com.example.leakcanaryissue.TestApp.onCreate(TestApp.kt:5)
        at org.robolectric.android.internal.AndroidTestEnvironment.lambda$installAndCreateApplication$2(AndroidTestEnvironment.java:350)
        at org.robolectric.util.PerfStatsCollector.measure(PerfStatsCollector.java:86)
        at org.robolectric.android.internal.AndroidTestEnvironment.installAndCreateApplication(AndroidTestEnvironment.java:350)
        at org.robolectric.android.internal.AndroidTestEnvironment.lambda$createApplicationSupplier$0(AndroidTestEnvironment.java:229)
        at org.robolectric.util.PerfStatsCollector.measure(PerfStatsCollector.java:53)
        at org.robolectric.android.internal.AndroidTestEnvironment.lambda$createApplicationSupplier$1(AndroidTestEnvironment.java:226)
        at com.google.common.base.Suppliers$NonSerializableMemoizingSupplier.get(Suppliers.java:167)
        at org.robolectric.RuntimeEnvironment.getApplication(RuntimeEnvironment.java:71)
        at org.robolectric.android.internal.AndroidTestEnvironment.setUpApplicationState(AndroidTestEnvironment.java:194)
        at org.robolectric.RobolectricTestRunner.beforeTest(RobolectricTestRunner.java:325)
        at org.robolectric.internal.SandboxTestRunner$2.lambda$evaluate$0(SandboxTestRunner.java:265)
        at org.robolectric.internal.bytecode.Sandbox.lambda$runOnMainThread$0(Sandbox.java:88)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at java.base/java.lang.Thread.run(Thread.java:829)

```


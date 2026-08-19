# Root Boost

Android root utility with a Home Screen widget.

## What it does

When BOOST is pressed, the app executes through `su`:

- force-stops third-party packages except Gboard
- runs `echo 3 > /proc/sys/vm/drop_caches`
- runs `am kill-all`

The device must already have working root access and a `su` binary/provider such as KernelSU or Magisk.

## Build with GitHub Actions

Push the project to GitHub. The workflow at `.github/workflows/build.yml` builds:

`app/build/outputs/apk/debug/app-debug.apk`

The APK is uploaded as the `RootBoost-debug` Actions artifact.

## Widget

Add the "Root Boost" widget to the Android Home Screen and tap BOOST.

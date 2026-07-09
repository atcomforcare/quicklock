# Quick Lock — one-tap screen lock tile

A tiny app that adds a **Lock** tile to your Quick Settings pull-down menu.
Tap the tile once and your screen locks — just like pressing the power button.

You do not need to install any coding software. GitHub builds the app for you.

## Get the app built (about 5 minutes of clicking)

1. Make a free account at https://github.com if you don't have one.
2. Create a new **empty** repository (any name, e.g. `quicklock`).
3. On the new repo page, click **uploading an existing file**. Drag in
   **all** the files and folders from this project (keep the folder structure).
4. Click **Commit changes**. The build starts automatically.
5. Open the **Actions** tab, click the running job, and wait for the green check.
6. Scroll to **Artifacts** and download **QuickLock-app**. Inside is `app-debug.apk`.

## Put it on your phone

1. Copy `app-debug.apk` to your Pixel (email it to yourself, or use a USB cable).
2. Tap the file. Android will ask to allow installing from this source — allow it.
3. Open the **Quick Lock** app and follow the two setup buttons:
   - Turn on the accessibility permission (this is what lets the tile lock the screen).
   - Add the Lock tile to Quick Settings.
4. Swipe down, and tap **Lock**. Done.

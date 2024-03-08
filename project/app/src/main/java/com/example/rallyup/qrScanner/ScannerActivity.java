package com.example.rallyup.qrScanner;

import android.app.Activity;
import android.os.Bundle;

import com.example.rallyup.R;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.ViewfinderView;
/**
 * This class contains the scanner customization and functionality of the QR Scanner
 * @author Reimark Ronabio
 * references: https://github.com/journeyapps/zxing-android-embedded
 */
public class ScannerActivity extends Activity {

    // variable for simplified interface for the functionality of the android camera and its API
    private CaptureManager capture;

    // variable for interface that enables the customization of scanner
    private DecoratedBarcodeView barcodeScannerView;

    // variable for custom view for the camera
    private ViewfinderView viewfinderView;
    /**
     * Initializes the qr scanning activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_activity);

        // linking the views
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        viewfinderView = findViewById(R.id.zxing_viewfinder_view);

        // create capture manager using scanner view
        capture = new CaptureManager(this, barcodeScannerView);

        // get intents
        capture.initializeFromIntent(getIntent(), savedInstanceState);

        // camera permission already called before hand, set to false
        capture.setShowMissingCameraPermissionDialog(false);

        // decode
        capture.decode();

        // check function
        changeLaserVisibility(false);
    }

    /**
     * Called when activity is resumed from a paused state
     */
    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    /**
     * Called when activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    /**
     * Called when activity is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    /**
     * Called whenever it is required to save the state of this instance
     * @param outState Bundle in which to place your saved state.
     *
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    /**
     * Enables the ability to enable/disable the scanning laser
     * @param visible is the boolean value for the visibility
     */
    public void changeLaserVisibility(boolean visible) {
        viewfinderView.setLaserVisibility(visible);
    }
}

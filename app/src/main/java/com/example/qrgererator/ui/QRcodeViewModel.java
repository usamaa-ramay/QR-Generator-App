package com.example.qrgererator.ui;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRcodeViewModel extends ViewModel {
    private Bitmap qrCodeBitmap;
    private BarcodeEncoder barcodeEncoder;

    public Bitmap getQrCodeBitmap() {
        return qrCodeBitmap;
    }
    public void generateQRCode(String inputText) throws WriterException {
        barcodeEncoder = new BarcodeEncoder();
        qrCodeBitmap = barcodeEncoder.encodeBitmap(inputText, BarcodeFormat.QR_CODE, 400, 400);
    }
}

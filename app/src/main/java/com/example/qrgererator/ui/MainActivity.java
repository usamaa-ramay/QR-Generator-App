package com.example.qrgererator.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.qrgererator.databinding.ActivityMainBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private QRcodeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBar.myToolBar);
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(QRcodeViewModel.class);

        // Restore QR code if it exists
        if (viewModel.getQrCodeBitmap() != null) {
            binding.codeQRIV.setImageBitmap(viewModel.getQrCodeBitmap());
            binding.QRCV.setVisibility(View.VISIBLE);
        }

        binding.inputET.setOnClickListener(view -> {

            binding.QRCV.setVisibility(View.GONE);
            binding.inputET.getText().clear();
        });

        binding.generateBtn.setOnClickListener(view -> {
            String inputText = binding.inputET.getText().toString().trim();

            if (!inputText.isEmpty()) {
                try {
                    viewModel.generateQRCode(inputText);
                    binding.codeQRIV.setImageBitmap(viewModel.getQrCodeBitmap());
                    binding.QRCV.setVisibility(View.VISIBLE);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            } else {
                binding.inputET.setError("Please enter text to generate QR code");
            }
        });
    }

}

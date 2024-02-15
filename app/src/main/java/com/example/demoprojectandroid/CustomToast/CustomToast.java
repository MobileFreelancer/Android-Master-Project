package com.example.demoprojectandroid.CustomToast;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.demoprojectandroid.R;
import com.example.demoprojectandroid.databinding.LayoutToastBinding;

public class CustomToast {

    LayoutToastBinding binding;

    private final Activity context;
    Toast toast;

    public CustomToast(Activity context, int gravity) {
        this.context = context;
        toast = new Toast(context);
        toast.setGravity(gravity, 0, 70);
        toast.setDuration(Toast.LENGTH_LONG);
        binding = LayoutToastBinding.inflate(context.getLayoutInflater());
        toast.setView(binding.getRoot());
    }

    public void showToast(String toastString) {
        if (toastString != null) {
            binding.tvToastTitle.setText(toastString);
        }

        toast.show();
        Animation animFadeIn = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);
        binding.llToastRoot.startAnimation(animFadeIn);
    }

    public void cancelToast() {
        toast.cancel();
    }

}
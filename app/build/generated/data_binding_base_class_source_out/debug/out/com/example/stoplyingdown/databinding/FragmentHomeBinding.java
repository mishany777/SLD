// Generated by view binder compiler. Do not edit!
package com.example.stoplyingdown.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.stoplyingdown.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton activityButton;

  @NonNull
  public final ImageButton foodButton;

  @NonNull
  public final ImageButton sleepButton;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView50;

  @NonNull
  public final TextView textView51;

  @NonNull
  public final TextView textView70;

  @NonNull
  public final ImageButton waterButton;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton activityButton, @NonNull ImageButton foodButton,
      @NonNull ImageButton sleepButton, @NonNull TextView textView3, @NonNull TextView textView50,
      @NonNull TextView textView51, @NonNull TextView textView70,
      @NonNull ImageButton waterButton) {
    this.rootView = rootView;
    this.activityButton = activityButton;
    this.foodButton = foodButton;
    this.sleepButton = sleepButton;
    this.textView3 = textView3;
    this.textView50 = textView50;
    this.textView51 = textView51;
    this.textView70 = textView70;
    this.waterButton = waterButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.activity_button;
      ImageButton activityButton = ViewBindings.findChildViewById(rootView, id);
      if (activityButton == null) {
        break missingId;
      }

      id = R.id.food_button;
      ImageButton foodButton = ViewBindings.findChildViewById(rootView, id);
      if (foodButton == null) {
        break missingId;
      }

      id = R.id.sleep_button;
      ImageButton sleepButton = ViewBindings.findChildViewById(rootView, id);
      if (sleepButton == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView50;
      TextView textView50 = ViewBindings.findChildViewById(rootView, id);
      if (textView50 == null) {
        break missingId;
      }

      id = R.id.textView51;
      TextView textView51 = ViewBindings.findChildViewById(rootView, id);
      if (textView51 == null) {
        break missingId;
      }

      id = R.id.textView70;
      TextView textView70 = ViewBindings.findChildViewById(rootView, id);
      if (textView70 == null) {
        break missingId;
      }

      id = R.id.water_button;
      ImageButton waterButton = ViewBindings.findChildViewById(rootView, id);
      if (waterButton == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, activityButton, foodButton,
          sleepButton, textView3, textView50, textView51, textView70, waterButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

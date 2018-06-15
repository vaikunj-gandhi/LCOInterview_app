package com.vaikunj.myinerview_app.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * Created by Chandresh on 15/03/2017.
 */

public class MyUtils {

    public static int menu_selection = 0;
    public static boolean LOG = true;
    public static String imageName = "Throat.png";
    public static final int TYPE_FULL = 0;
    public static final int TYPE_HALF = 1;
    public static final int TYPE_QUARTER = 2;
    public static final int TYPE_THIRD = 3;


    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }





    public static void show_message(String msg, final TextView tv_msg)
    {

        tv_msg.setText(msg);
        tv_msg.clearAnimation();

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(3000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(3000);
        fadeOut.setDuration(3000);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);

        animation.setAnimationListener( new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                tv_msg.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                tv_msg.setVisibility(View.VISIBLE);

            }

        });
        tv_msg.setAnimation(animation);
    }


    /**
     * Checks if the Internet connection is available.
     *
     * @return Returns true if the Internet connection is available. False otherwise.
     * *
     */
    public static boolean isInternetAvailable(Context ctx) {
        ConnectivityManager check = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (check != null) {
            NetworkInfo[] info = check.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                        //Toast.makeText(context, "Internet is connected", Toast.LENGTH_SHORT).show();
                    }
        }
        return false;
    }


    public static void startActivity(Context oneActivity, Class secondActivity , boolean finishCurrentActivity)
    {
        Intent i=new Intent(oneActivity,secondActivity);
        oneActivity.startActivity(i);
        if(finishCurrentActivity)
            ((Activity) oneActivity).finish();

       // ((Activity) oneActivity).overridePendingTransition(android.R.anim.slide_in_right, android.R.anim.slide_out_left);

    }
    public static void finishActivity(Context activity, boolean lefttorightanimation)
    {
        ((Activity) activity).finish();
        if(lefttorightanimation)
            ((Activity) activity).overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
       // else
         //   ((Activity) activity).overridePendingTransition(android.R.anim.slide_in_right, android.R.anim.slide_out_left);


    }
    public static void startActivity(Context oneActivity, Class secondActivity , boolean finishCurrentActivity, boolean lefttorightanimation)
    {
        Intent i=new Intent(oneActivity,secondActivity);
        oneActivity.startActivity(i);
        if(finishCurrentActivity)
            ((Activity) oneActivity).finish();

       // if(lefttorightanimation)
          //  ((Activity) oneActivity).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
          //else
        //((Activity) oneActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }





    public static void showSnackbar(Context c, String msg, View v)
    {
        Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();

      //  sbView.setBackgroundColor(ContextCompat.getColor(c, android.R.color.colorAccent));
        snackbar.show();
    }







    public Dialog showMessageOKCancel(Context context,String message,DialogInterface.OnClickListener okListener) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("Ok",okListener);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();

        return alert;
    }

}

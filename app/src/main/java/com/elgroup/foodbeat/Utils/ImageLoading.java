package com.elgroup.foodbeat.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elgroup.foodbeat.CustomDialog.NewtonCradleLoading;
import com.elgroup.foodbeat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ImageLoading {

    private ImageLoadingListener imageListener;
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    NewtonCradleLoading imageProgress;

    HashMap<ImageView, NewtonCradleLoading> imageProgressBar;

    public ImageLoading(Context context) {
        // TODO Auto-generated constructor stub

        imageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.failed)
                .showStubImage(R.drawable.red_background)
                .showImageForEmptyUri(R.drawable.failed).cacheInMemory()
                .cacheOnDisc().build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        imageListener = new ImageDisplayListener();

        imageProgressBar = new HashMap<ImageView, NewtonCradleLoading>();
    }

    public ImageLoading(Context context, boolean addInCache) {
        // TODO Auto-generated constructor stub

        imageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.failed)
                .showStubImage(R.drawable.red_background)
                .showImageForEmptyUri(R.drawable.failed).build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        imageListener = new ImageDisplayListener();

        imageProgressBar = new HashMap<ImageView, NewtonCradleLoading>();
    }

    public ImageLoading(Context context, int radius) {
        // TODO Auto-generated constructor stub

        imageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.failed)
                .showStubImage(R.drawable.red_background)
                .showImageForEmptyUri(R.drawable.failed).displayer(new RoundedBitmapDisplayer(radius)).build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        imageListener = new ImageDisplayListener();

        imageProgressBar = new HashMap<ImageView, NewtonCradleLoading>();
    }

    public void LoadImage(String Url, ImageView imageView,
                          NewtonCradleLoading imageProgress) {
        imageProgressBar.put(imageView, imageProgress);
        imageLoader.displayImage(Url, imageView, options, imageListener);
    }

    private class ImageDisplayListener extends
            SimpleImageLoadingListener {

        final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            imageProgress = imageProgressBar.get(view);

            if (imageProgress != null) {
                imageProgress.stop();
                imageProgress.setVisibility(View.GONE);
                imageProgressBar.remove(view);
                ((RelativeLayout) imageProgress.getParent()).removeView(imageProgress);
            }
            if (loadedImage != null) {

                ImageView imageView = (ImageView) view;

                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }

        @Override
        public void onLoadingFailed(String imageUri, View view,
                                    FailReason failReason) {
            // Empty implementation

            imageProgress = imageProgressBar.get(view);
            if (imageProgress != null) {
                imageProgress.stop();
                imageProgress.setVisibility(View.GONE);
                imageProgressBar.remove(view);
                ((RelativeLayout) imageProgress.getParent()).removeView(imageProgress);
            }
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            // Empty implementation
            imageProgress = imageProgressBar.get(view);
            if (imageProgress != null) {
                imageProgress.setVisibility(View.GONE);
                imageProgressBar.remove(view);
                ((RelativeLayout) imageProgress.getParent()).removeView(imageProgress);
            }
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {
            imageProgress = imageProgressBar.get(view);
            if (imageProgress != null) {
                imageProgress.setVisibility(View.VISIBLE);
                imageProgress.start();
            }
        }
    }
}

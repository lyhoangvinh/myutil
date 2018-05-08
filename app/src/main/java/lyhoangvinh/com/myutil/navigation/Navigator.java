package lyhoangvinh.com.myutil.navigation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ------
 *
 * FILE CHANGED 2017 Tailored Media GmbH
 *
 * */
public interface Navigator {
    String EXTRA_ARG = "_args";

    void finishActivity();
    void startActivity(@NonNull Intent intent);
    void startActivity(@NonNull String action);
    void startActivity(@NonNull String action, @NonNull Uri uri);
    void startActivity(@NonNull Class<? extends Activity> activityClass);
    void startActivity(@NonNull Class<? extends Activity> activityClass, @NonNull PlainConsumer<Intent> setArgsAction);
    void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args);
    void startActivity(@NonNull Class<? extends Activity> activityClass, Parcelable args);
    void startActivity(@NonNull Class<? extends Activity> activityClass, @NonNull String arg);
    void startActivity(@NonNull Class<? extends Activity> activityClass, int arg);
    void startActivityWithTransition(@NonNull Class<? extends Activity> activityClass,
                                     @NonNull PlainConsumer<Intent> setArgsAction,
                                     boolean includeNestedChildren, boolean includeStatusBar, View... views);

    void startActivityForResult(@NonNull Class<? extends Activity> activityClass, int requestCode);
    void startActivityForResult(@NonNull Class<? extends Activity> activityClass, @NonNull PlainConsumer<Intent> setArgsAction, int requestCode);
    void startActivityForResult(@NonNull Class<? extends Activity> activityClass, @NonNull Parcelable arg, int requestCode);
    void startActivityForResult(@NonNull Class<? extends Activity> activityClass, @NonNull String arg, int requestCode);
    void startActivityForResult(@NonNull Class<? extends Activity> activityClass, int arg, int requestCode);

    void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment, View... transitionViews);
    void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment, Bundle args, View... transitionViews);
    void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag, Bundle args, View... transitionViews);
    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment, Bundle args, String backstackTag, View... transitionViews);
    void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag, Bundle args, String backstackTag, View... transitionViews);

    @Nullable
    <T extends Fragment> T findFragmentByTag(@NonNull String tag);

    @Nullable
    <T extends Fragment> T findFragmentById(@IdRes int containerId);
}

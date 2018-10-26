package com.stkent.a11yinfo;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.provider.Settings;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
import static android.content.Context.ACCESSIBILITY_SERVICE;
import static android.content.Context.WINDOW_SERVICE;
import static android.os.Build.VERSION.SDK_INT;
import static android.provider.Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED;
import static android.util.DisplayMetrics.DENSITY_DEVICE_STABLE;

/**
 * Entry point to all information provided by the A11yInfo library.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class A11yInfo {

    @NonNull
    private final Context appContext;

    @NonNull
    private final AccessibilityManager a11yManager;

    @NonNull
    private final WindowManager windowManager;

    public A11yInfo(@NonNull final Context context) {
        appContext = context.getApplicationContext();

        //noinspection ConstantConditions
        a11yManager = (AccessibilityManager) context.getSystemService(ACCESSIBILITY_SERVICE);

        //noinspection ConstantConditions
        windowManager = (WindowManager) appContext.getSystemService(WINDOW_SERVICE);
    }

    /**
     * @return the user-specified display scale. Values less than 1 indicate the user has shrunk
     * all UI; values greater than 1 indicate the user has magnified all UI.
     */
    @FloatRange(from = 0, to = Float.MAX_VALUE)
    public float getDisplayScale() {
        if (SDK_INT >= 24) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return (float) displayMetrics.densityDpi / DENSITY_DEVICE_STABLE;
        } else {
            return 1.0f;
        }
    }

    /**
     * @return a list of all accessibility services currently enabled on the host device. Note
     * that some services (e.g. TalkBack) can be temporarily suspended. The list returned by
     * this method will include all enabled-but-suspended services.
     */
    @NonNull
    public Set<A11yService> getEnabledA11yServices() {
        final Set<A11yService> result = new HashSet<>();

        for (final AccessibilityServiceInfo info :
                a11yManager.getEnabledAccessibilityServiceList(FEEDBACK_ALL_MASK)) {

            final Set<A11yFeedbackType> feedbackTypes = A11yFeedbackType.parse(info.feedbackType);
            result.add(new A11yService(info.getId(), feedbackTypes));
        }

        return result;
    }

    /**
     * @param feedbackType an accessibility service feedback type
     * @return a list of all accessibility services of the given type currently enabled on the host
     * device. Note that some services (e.g. TalkBack) can be temporarily suspended. The
     * list returned by this method will include all enabled-but-suspended services.
     */
    @NonNull
    public Set<A11yService> getEnabledA11yServices(@NonNull final A11yFeedbackType feedbackType) {
        final Set<A11yService> result = new HashSet<>();

        for (final AccessibilityServiceInfo info :
                a11yManager.getEnabledAccessibilityServiceList(feedbackType.getFlag())) {

            final Set<A11yFeedbackType> feedbackTypes = A11yFeedbackType.parse(info.feedbackType);
            result.add(new A11yService(info.getId(), feedbackTypes));
        }

        return result;
    }

    /**
     * @return the user-specified font scale. Values less than 1 indicate the user has shrunk all
     * fonts; values greater than 1 indicate the user has magnified all fonts.
     */
    @FloatRange(from = 0, to = Float.MAX_VALUE)
    public float getFontScale() {
        return appContext.getResources().getConfiguration().fontScale;
    }

    /**
     * @return true if at least one accessibility service is currently enabled; false otherwise.
     * Note that some services (e.g. TalkBack) can be temporarily suspended. This method
     * will return true if an accessibility service is enabled-but-suspended.
     */
    public boolean isA11yServiceEnabled() {
        return !getEnabledA11yServices().isEmpty();
    }

    /**
     * @param feedbackType an accessibility service feedback type
     * @return true if at least one accessibility service of the given type is currently enabled;
     * false otherwise. Note that some services (e.g. TalkBack) can be temporarily
     * suspended. This method will return true if an accessibility service of the given type
     * is enabled-but-suspended.
     */
    public boolean isA11yServiceEnabled(@NonNull final A11yFeedbackType feedbackType) {
        return !getEnabledA11yServices(feedbackType).isEmpty();
    }

    /**
     * @param serviceId an accessibility service id (the flattened {@code ComponentName} of
     *                  the service)
     * @return true if an accessibility service with the given id is currently enabled; false
     * otherwise. Note that some services (e.g. TalkBack) can be temporarily suspended.
     * This method will return true if an accessibility with the given id is
     * enabled-but-suspended.
     */
    public boolean isA11yServiceEnabled(@NonNull final String serviceId) {
        final List<A11yService> result = new ArrayList<>();

        for (final AccessibilityServiceInfo info :
                a11yManager.getEnabledAccessibilityServiceList(FEEDBACK_ALL_MASK)) {

            if (serviceId.equals(info.getId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if display color inversion is enabled; false otherwise. Always returns false
     * on host devices running KitKat or lower as this feature was first introduced in
     * Lollipop.
     */
    public boolean isDisplayInversionEnabled() {
        if (SDK_INT < 21) {
            return false;
        }

        try {
            return Settings.Secure.getInt(
                    appContext.getContentResolver(),
                    ACCESSIBILITY_DISPLAY_INVERSION_ENABLED) == 1;
        } catch (final Settings.SettingNotFoundException ignored) {
            return false;
        }
    }

    /**
     * @return true if touch exploration is enabled; false otherwise.
     */
    public boolean isTouchExplorationEnabled() {
        return a11yManager.isTouchExplorationEnabled();
    }

}

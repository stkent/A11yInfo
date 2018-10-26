package com.stkent.a11yinfo;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_AUDIBLE;
import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_BRAILLE;
import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_GENERIC;
import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_HAPTIC;
import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_SPOKEN;
import static android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_VISUAL;

/**
 * A type-safe wrapper around {@code AccessibilityServiceInfo} feedback type flags.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public enum A11yFeedbackType {

    AUDIBLE(FEEDBACK_AUDIBLE),
    BRAILLE(FEEDBACK_BRAILLE),
    GENERIC(FEEDBACK_GENERIC),
    HAPTIC(FEEDBACK_HAPTIC),
    SPOKEN(FEEDBACK_SPOKEN),
    VISUAL(FEEDBACK_VISUAL);

    private final int flag;

    A11yFeedbackType(final int flag) {
        this.flag = flag;
    }

    @NonNull
    public static Set<A11yFeedbackType> parse(final int serviceFlag) {
        final Set<A11yFeedbackType> result = new HashSet<>();

        for (final A11yFeedbackType type : values()) {
            final int typeFlag = type.flag;

            if ((serviceFlag & typeFlag) == typeFlag) {
                result.add(type);
            }
        }

        return result;
    }

    public int getFlag() {
        return flag;
    }

}

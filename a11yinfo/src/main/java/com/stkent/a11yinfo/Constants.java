package com.stkent.a11yinfo;

import android.support.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class Constants {

    /**
     * The accessibility service ID for Google's Select to Speak service.
     */
    @NonNull
    public static final String SELECT_TO_SPEAK_SERVICE_ID
            = "com.google.android.marvin.talkback/com.google.android.accessibility.selecttospeak.SelectToSpeakService";

    /**
     * The accessibility service ID for Google's Switch Access service.
     */
    @NonNull
    public static final String SWITCH_ACCESS_SERVICE_ID
            = "com.google.android.marvin.talkback/com.android.switchaccess.SwitchAccessService";

    /**
     * The accessibility service ID for Google's TalkBack service.
     */
    @NonNull
    public static final String TALKBACK_SERVICE_ID
            = "com.google.android.marvin.talkback/.TalkBackService";

    private Constants() {
        // This constructor intentionally left blank.
    }

}

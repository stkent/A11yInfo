package com.stkent.a11yinfo;

import android.support.annotation.NonNull;

import java.util.Set;

/**
 * A collection of the most important properties of an accessibility service.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class A11yService {

    @NonNull
    private final String id;

    @NonNull
    private final Set<A11yFeedbackType> feedbackTypes;

    public A11yService(@NonNull final String id, @NonNull final Set<A11yFeedbackType> feedbackTypes) {
        this.id = id;
        this.feedbackTypes = feedbackTypes;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public Set<A11yFeedbackType> getFeedbackTypes() {
        return feedbackTypes;
    }

    @NonNull
    @Override
    public String toString() {
        return "A11yService{" +
                "id='" + id + '\'' +
                ", feedbackTypes=" + feedbackTypes +
                '}';
    }

}

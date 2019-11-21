package com.sybetech.business.frameworks;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class MyHamcrestMatcher extends TypeSafeMatcher<Integer> {
    private Integer expected;

    public MyHamcrestMatcher(final Integer expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(final Integer actual) { // Matching logic
        //TODO impl me
        if(expected >5) return true;

        return expected == actual;
    }

    @Override
    public void describeTo(final Description description) { // For error msgs
        description.appendValue(expected);
    }

    public static MyHamcrestMatcher myEqualTo(Integer expected) {
        // TODO impl me
        return new MyHamcrestMatcher(expected);
    }
}

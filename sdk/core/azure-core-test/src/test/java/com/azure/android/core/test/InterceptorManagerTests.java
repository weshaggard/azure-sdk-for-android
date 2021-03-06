// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.core.test;

import org.junit.jupiter.api.Test;

import static com.azure.android.core.test.FakeTestClass.DONOTRECORD_FALSE_SKIPINPLAYBACK;
import static com.azure.android.core.test.FakeTestClass.METHOD_WITHOUT_DONOTRECORD;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for {@link InterceptorManager}.
 */
public class InterceptorManagerTests {
    /**
     * Validates that {@link InterceptorManager#getRecordedData()} is {@code null} when testing in {@link
     * TestMode#LIVE}.
     */
    @Test
    public void recordedDataIsNullInLiveMode() {
        assertNull(new InterceptorManager(new TestContextManager(METHOD_WITHOUT_DONOTRECORD, null, TestMode.LIVE))
            .getRecordedData());
        assertNull(new InterceptorManager(new TestContextManager(DONOTRECORD_FALSE_SKIPINPLAYBACK, null, TestMode.LIVE))
            .getRecordedData());
    }

    /**
     * Validates that {@link InterceptorManager#getRecordedData()} is {@code null} when {@code doNotRecord} is passed as
     * {@code true}.
     */
    @Test
    public void recordedDataIsNullWhenDoNotRecord() {
        assertNull(new InterceptorManager(new TestContextManager(DONOTRECORD_FALSE_SKIPINPLAYBACK, null, TestMode.RECORD))
            .getRecordedData());
        assertNull(new InterceptorManager(new TestContextManager(DONOTRECORD_FALSE_SKIPINPLAYBACK, null, TestMode.LIVE))
            .getRecordedData());
        assertNull(new InterceptorManager(new TestContextManager(DONOTRECORD_FALSE_SKIPINPLAYBACK, null, TestMode.PLAYBACK))
            .getRecordedData());
//        assertNull(new InterceptorManager("testName", new HashMap<>(), true).getRecordedData());
    }
}

package com.example.lab8week.ex7;

import com.example.lab8week.ex7.component.RequestTrace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Request Scope example (ex7).
 * Unit tests for the RequestTrace component.
 */
class RequestTraceTest {

    @Test
    void testRequestTraceGeneratesUniqueRequestId() {
        RequestTrace requestTrace = new RequestTrace();
        String requestId = requestTrace.getRequestId();
        
        assertNotNull(requestId);
        assertFalse(requestId.isEmpty());
    }

    @Test
    void testTwoRequestTracesHaveDifferentIds() {
        RequestTrace requestTrace1 = new RequestTrace();
        RequestTrace requestTrace2 = new RequestTrace();
        
        assertNotEquals(requestTrace1.getRequestId(), requestTrace2.getRequestId());
    }

    @Test
    void testRequestIdIsUUID() {
        RequestTrace requestTrace = new RequestTrace();
        String requestId = requestTrace.getRequestId();
        
        // UUID format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        assertTrue(requestId.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @Test
    void testRequestIdIsFinal() {
        RequestTrace requestTrace = new RequestTrace();
        String firstCall = requestTrace.getRequestId();
        String secondCall = requestTrace.getRequestId();
        
        // The requestId is final, so it should return the same value
        assertEquals(firstCall, secondCall);
    }

    @Test
    void testMultipleRequestTracesAllHaveUniqueIds() {
        RequestTrace[] traces = new RequestTrace[10];
        for (int i = 0; i < 10; i++) {
            traces[i] = new RequestTrace();
        }
        
        // Verify all IDs are unique
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                assertNotEquals(traces[i].getRequestId(), traces[j].getRequestId(),
                        "RequestTrace " + i + " and " + j + " should have different IDs");
            }
        }
    }
}

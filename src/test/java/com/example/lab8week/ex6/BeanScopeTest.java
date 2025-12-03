package com.example.lab8week.ex6;

import com.example.lab8week.ex6.component.IdBean;
import com.example.lab8week.ex6.service.ServiceA;
import com.example.lab8week.ex6.service.ServiceB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Bean Scope example (ex6).
 * Unit tests that demonstrate singleton vs prototype behavior.
 */
class BeanScopeTest {

    @Test
    void testIdBeanGeneratesUniqueId() {
        IdBean idBean = new IdBean();
        String id = idBean.getId();
        
        assertNotNull(id);
        assertFalse(id.isEmpty());
    }

    @Test
    void testTwoIdBeansHaveDifferentIds() {
        // When created manually (not through Spring), each instance has a unique ID
        IdBean idBean1 = new IdBean();
        IdBean idBean2 = new IdBean();
        
        assertNotEquals(idBean1.getId(), idBean2.getId());
    }

    @Test
    void testServiceAReturnsIdWithPrefix() {
        IdBean idBean = new IdBean();
        ServiceA serviceA = new ServiceA(idBean);
        
        String result = serviceA.getId();
        
        assertTrue(result.startsWith("ServiceA: "));
        assertTrue(result.contains(idBean.getId()));
    }

    @Test
    void testServiceBReturnsIdWithPrefix() {
        IdBean idBean = new IdBean();
        ServiceB serviceB = new ServiceB(idBean);
        
        String result = serviceB.getId();
        
        assertTrue(result.startsWith("ServiceB: "));
        assertTrue(result.contains(idBean.getId()));
    }

    @Test
    void testServicesWithSameIdBeanHaveSameId() {
        // Simulating singleton scope: same IdBean instance shared between services
        IdBean sharedIdBean = new IdBean();
        ServiceA serviceA = new ServiceA(sharedIdBean);
        ServiceB serviceB = new ServiceB(sharedIdBean);
        
        String idFromA = serviceA.getId().replace("ServiceA: ", "");
        String idFromB = serviceB.getId().replace("ServiceB: ", "");
        
        assertEquals(idFromA, idFromB);
    }

    @Test
    void testServicesWithDifferentIdBeansHaveDifferentIds() {
        // Simulating prototype scope: each service gets its own IdBean instance
        IdBean idBean1 = new IdBean();
        IdBean idBean2 = new IdBean();
        ServiceA serviceA = new ServiceA(idBean1);
        ServiceB serviceB = new ServiceB(idBean2);
        
        String idFromA = serviceA.getId().replace("ServiceA: ", "");
        String idFromB = serviceB.getId().replace("ServiceB: ", "");
        
        assertNotEquals(idFromA, idFromB);
    }

    @Test
    void testIdBeanIdIsUUID() {
        IdBean idBean = new IdBean();
        String id = idBean.getId();
        
        // UUID format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        assertTrue(id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }
}

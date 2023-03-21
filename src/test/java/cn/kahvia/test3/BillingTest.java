package cn.kahvia.test3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingTest {

    private static final double DELTA = 0.0001;


    @Test
    @DisplayName("二十分钟以内，不含夏令时转换")
    public void testLessThan20Minutes() {
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-01-01 12:00:00", "2023-01-01 12:10:00",1,1,false,false);
        assertEquals(0.5, cost, DELTA);
    }

    @Test
    @DisplayName("二十分钟以内，含夏令时开始转换")
    public void testLessThan20MinutesWithSummerStart() {
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-03-05 01:55:00", "2023-03-05 03:05:00",1,1,false,true);
        assertEquals(0.5, cost, DELTA);
    }

    @Test
    @DisplayName("二十分钟以内，含夏令时结束转换")
    public void testLessThan20MinutesWithSummerEnd() {
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-11-05 02:30:00", "2023-11-05 02:40:00",1,1,true,true);
        assertEquals(0.5, cost, DELTA);
    }


    @Test
    @DisplayName("二十分钟以上，不含夏令时转换")
    public void testGreaterThan20Minutes() {
        //二十分钟一美元，超出每分钟0.10美元
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-03-05 03:00:00", "2023-03-05 03:25:00",1,1,true,true);
        assertEquals(1.5, cost, DELTA);
    }

    @Test
    @DisplayName("二十分钟以上，含夏令时开始转换")
    public void testGreaterThan20MinutesWithSummerStart() {
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-03-05 01:45:00", "2023-03-05 03:10:00",1,1,false,true);
        assertEquals(1.5, cost, DELTA);
    }


    @Test
    @DisplayName("二十分钟以上，含夏令时结束转换")
    public void testGreaterThan20MinutesWithSummerEnd() {
        Billing billing = new Billing();
        double cost = billing.calculateCost("2023-11-05 02:30:00", "2023-11-05 02:40:00",1,1,false,true);
        assertEquals(6.0, cost, DELTA);//0.5 or 6?
    }

}

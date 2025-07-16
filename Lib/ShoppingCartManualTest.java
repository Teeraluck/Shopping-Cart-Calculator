package Lib;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        simpleCart.clear();
        // Test4 : (ซื้อ 1 แถม 1)
        simpleCart.add(new CartItem("BOGO", "Bread", 25.0, 2)); //25 * 2 = 50 - 25 = 25
        simpleCart.add(new CartItem("BOGO", "Milk", 15.0, 1)); //15
        double total4 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total4 == 40.0) {
            System.out.println("PASSED: Simple cart total is correct (40.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 40.0 but got " + total4);
            failedCount++;
        }

        simpleCart.clear();
        // Test5 : (ส่วนลดเมื่อซื้อเยอะ)
        simpleCart.add(new CartItem("BULK", "Bread", 25.0, 5)); // 25 * 5 = 125
        simpleCart.add(new CartItem("BULK", "Milk", 15.0, 6)); // (15 * 6) - ((15 * 6)/10) = 81
        double total5 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total5 == 206.0) {
            System.out.println("PASSED: Simple cart total is correct (206.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 206.0 but got " + total5);
            failedCount++;
        }

        simpleCart.clear();
        // Test 6: ตะกร้ามี price หรือ quantity เป็น 0 หรือ ติดลบ
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 1)); // 25
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 0));  // 0
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, -1));  // -15
        double total6 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total6 == 25.0) {
            System.out.println("PASSED: Simple cart total is correct (25.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 25.0 but got " + total6);
            failedCount++;
        }

        simpleCart.clear();
        // Test 7: คำนวณ มีทั้ง ไม่มีส่วนลดและมีส่วนลด
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 3)); // (25 * 3) = 75
        simpleCart.add(new CartItem("BOGO", "Butter", 10.0, 5)); // (10 * 5) - 20 = 30
        simpleCart.add(new CartItem("BULK", "Milk", 15.0, 6));  // (15 * 6) - ((15 * 6) / 10) = 81
        double total7 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total7 == 186.0) {
            System.out.println("PASSED: Simple cart total is correct (186.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 186.0 but got " + total7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
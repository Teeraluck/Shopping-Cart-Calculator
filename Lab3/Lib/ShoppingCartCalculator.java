package Lib;
import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * @param รับ AlleyList CartItem ที่มีสินค้าและช้อมูลของสินค้านั้น ประกอบไปด้วยรหัสการซื้อ ชื่อสินค้า ราคาต่อชิ้น จำนวนที่ซื้อ
     * @return ราคาสุทธิของสินค้าทั้งหมดใน CartItem โดยเป็นราคาที่รวมส่วนลด
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        if (items==null || items.size() <= 0) {
            return 0.0;
        }
        double Total = 0.0;
            for (CartItem i : items) {
                if (i.price() <= 0 || i.quantity() <= 0){
                    continue;
                }
                if(i.sku() == "NORMAL"){
                    Total += i.price()*i.quantity();
                }
                else if(i.sku() == "BOGO"){
                    Total += (i.price()*i.quantity()) - (i.price() * (Math.ceil(i.quantity()/2)));
                }
                else if(i.sku() == "BULK"){
                    if (i.quantity() >= 6){//6ชิ้นขึ้นไป
                        Total += i.price()*i.quantity()  - ((i.price() * i.quantity())/10);
                    }
                else Total += i.price()*i.quantity();
                }
            }
        return Total;
    }
}

package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Print {
    public static void printAllOrderDetails() {
        // 1. Ana section altındaki tüm bilgi kutucuklarını seçiyoruz
        // Hem üstteki 'order-box' hem de özet kısmındaki 'o-details' divlerini kapsar
        List<WebElement> detailBoxes = Driver.getDriver().findElements(By.cssSelector(".order-box, .o-details, .order-detail-box"));

        System.out.println("--- SİPARİŞ DETAYLARI LİSTELENİYOR ---");

        for (WebElement box : detailBoxes) {
            try {
                // Her kutunun içindeki p ve span etiketlerini buluyoruz
                // Bazı kutularda birden fazla p veya span olabilir, bu yüzden liste olarak alıyoruz
                List<WebElement> labels = box.findElements(By.tagName("p"));
                List<WebElement> values = box.findElements(By.tagName("span"));

                for (int i = 0; i < labels.size(); i++) {
                    String labelText = labels.get(i).getText().trim();

                    // Eğer karşılık gelen bir span varsa yazdır
                    if (i < values.size()) {
                        String valueText = values.get(i).getText().trim();
                        if (!labelText.isEmpty()) {
                            System.out.println(labelText + " : " + valueText);
                        }
                    } else {
                        // Span yoksa sadece p etiketini yazdır (Örn: Adres başlıkları)
                        if (!labelText.isEmpty()) {
                            System.out.println("Başlık: " + labelText);
                        }
                    }
                }
            } catch (Exception e) {
                // Boş veya geçersiz kutuları atlamak için
                continue;
            }
        }
        System.out.println("-------------------------------------");
    }
}

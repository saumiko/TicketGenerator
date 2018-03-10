import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import org.apache.pdfbox.Overlay;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author saumiko
 */
public class Generate {
    public static String serialNumber, name, address, mail, mobile, fb, blood, bars;
    
    public static void generateSerial()
    {
        BarGenerator barobj = new BarGenerator();
        bars = barobj.GenerateBar();
        try{
            Barcode barcode = BarcodeFactory.createCode128B(bars);
            barcode.setBarHeight(60);
            barcode.setBarWidth(1);
            File imgFile = new File("serial.png");
            BarcodeImageHandler.savePNG(barcode, imgFile);
        }
        catch(Exception e){}
        BufferedImage bufferedImage;	
	try {
	  bufferedImage = ImageIO.read(new File("serial.png"));
	  BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	  newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	  ImageIO.write(newBufferedImage, "jpg", new File("serial.jpg"));		
	} catch (IOException e) {
	  e.printStackTrace();
	}
        File pngimg = new File("serial.png");
        try{
            pngimg.delete();
        }
        catch(Exception e){}
        processTicket();
    }
    
    public static void processTicket()
    {
        String fileName = "Tickets/"+bars+".pdf";
        String watermark = "Format/watermark.pdf";
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
            PDXObjectImage image = new PDJpeg(doc, new FileInputStream("serial.jpg"));
            doc.addPage(page);
            PDRectangle rect = page.getMediaBox();
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            
            //Drawing Barcode
            content.drawImage(image, 380, 660);            
            //content.drawImage(image, 20, 380);
            //content.drawImage(image, 20, 245);
            
            //Breakfast Coupon
            content.beginText();
            content.setFont(fontBold, 20);
            content.moveTextPositionByAmount(80, 445);
            content.drawString(bars);
            content.endText();
            
            //Launch Coupon
            content.beginText();
            content.setFont(fontBold, 20);
            content.moveTextPositionByAmount(80, 300);
            content.drawString(bars);
            content.endText();
            
            //Ticket Holder Copy
            content.beginText();
            content.setFont(fontBold, 20);
            content.moveTextPositionByAmount(80, 55);
            content.drawString(bars);
            content.endText();
            
            //Box Copy
            content.beginText();
            content.setFont(fontBold, 20);
            content.moveTextPositionByAmount(370, 55);
            content.drawString(bars);
            content.endText();
            
            //Main Serial
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(500, 630);
            content.drawString(serialNumber);
            content.endText();
            
            //Breakfast Serial
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(500, 392);
            content.drawString(serialNumber);
            content.endText();
            
            //Lunch Serial
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(500, 259);
            content.drawString(serialNumber);
            content.endText();
            
            //Box Serial
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(420, 143);
            content.drawString(serialNumber);
            content.endText();
            
            //Raffel Serial
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(150, 145);
            content.drawString(serialNumber);
            content.endText();
            
            /*
                Main info begins here
            */
            
            //Name
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597);
            content.drawString(name);
            content.endText();
            
            //Address
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597-20);
            content.drawString(address);
            content.endText();
            
            //mail
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597-40);
            content.drawString(mail);
            content.endText();
            
            //fb
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597-60);
            content.drawString(fb);
            content.endText();
            
            //phone
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597-80);
            content.drawString(mobile);
            content.endText();
            
            //blood
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(200, 597-100);
            content.drawString(blood);
            content.endText();
            
            /*
                Main info ends here & Other names begins.
            */
            
            //Breakfast Name
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(140, 355);
            content.drawString(name);
            content.endText();
            
            //Lunch Name
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(140, 227);
            content.drawString(name);
            content.endText();
            
            //Ticket Name
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(110, 98);
            content.drawString(name);
            content.endText();
            
            //Box Name
            content.beginText();
            content.setFont(fontBold, 15);
            content.moveTextPositionByAmount(370, 93);
            content.drawString(name);
            content.endText();
            
            content.close();
            
            PDDocument watermarkDoc = PDDocument.load(watermark);
            
            Overlay overlay = new Overlay();
            overlay.overlay(doc,watermarkDoc);
            watermarkDoc.save(fileName);
            
            doc.close();
            watermarkDoc.close();
        }
        catch(Exception e){}
        
        File file3 = new File("serial.jpg");
        try{
            file3.delete();
        }
        catch(Exception e){}
    }
}

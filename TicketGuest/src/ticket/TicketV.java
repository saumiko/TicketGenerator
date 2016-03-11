package ticket;

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

public class TicketV {
    public static String Serial, Name, Txn;
    
    public static void Disp()
    {
        try{
            Barcode barcode = BarcodeFactory.createCode128B(Serial);
            barcode.setBarHeight(60);
            barcode.setBarWidth(1);
            File imgFile = new File("testsize.png");
            BarcodeImageHandler.savePNG(barcode, imgFile);
        }
        catch(Exception e){}
        BufferedImage bufferedImage;	
	try {
	  bufferedImage = ImageIO.read(new File("testsize.png"));
	  BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	  newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	  ImageIO.write(newBufferedImage, "jpg", new File("testsize.jpg"));		
	} catch (IOException e) {
	  e.printStackTrace();
	}
        processTicket(Name, Txn);
    }
    
    private static void processTicket(String name, String txn)
    {
        String fileName = "Ticket.pdf";
        String watermark = "watermark.pdf";
        File file = new File("testsize.png");
        File file2 = new File("Ticket.pdf");
        try{
            file.delete();
            file2.delete();
        }
        catch(Exception e){}
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
            PDXObjectImage image = new PDJpeg(doc, new FileInputStream("testsize.jpg"));
            doc.addPage(page);
            PDRectangle rect = page.getMediaBox();
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            
            content.beginText();
            content.setFont(fontBold, 10);
            content.moveTextPositionByAmount(180, 557);
            content.drawString(name);
            content.endText();
            
            content.beginText();
            content.setFont(fontBold, 10);
            content.moveTextPositionByAmount(208, 544);
            content.drawString(txn);
            content.endText();
            
            content.drawImage(image, 400, 650);
            
            content.close();
            
            PDDocument watermarkDoc = PDDocument.load(watermark);
            
            Overlay overlay = new Overlay();
            overlay.overlay(doc,watermarkDoc);
            watermarkDoc.save(fileName);
            
            doc.close();
            watermarkDoc.close();
        }
        catch(Exception e){}
        
        File file3 = new File("testsize.jpg");
        try{
            file3.delete();
        }
        catch(Exception e){}
    }
}
